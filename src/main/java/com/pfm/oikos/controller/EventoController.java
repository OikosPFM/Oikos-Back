package com.pfm.oikos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Evento;
import com.pfm.oikos.entity.Instalacion;
import com.pfm.oikos.exception.EventoNotFoundException;
import com.pfm.oikos.service.EventoService;

@RestController
@RequestMapping(path = "api/v1/eventos")
public class EventoController {

  @Autowired
  private EventoService eventoService;
  
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public ResponseEntity<List<Evento>> getAllEventos() {
      List<Evento> eventos = eventoService.getAllEventos();
      for(int i=0;i<eventos.size();i++) {
      }
      return new ResponseEntity<>(eventos, HttpStatus.OK);
  }
  
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/finca/{id}")
  public ResponseEntity<List<Evento>> getEventosByFincaId(@PathVariable("id") Integer fincaId) {
      List<Evento> eventos = eventoService.getEventosByFincaID(fincaId);
      return new ResponseEntity<>(eventos, HttpStatus.OK);
  }
  
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Evento createEvento(@RequestBody Evento evento) {
    Evento newEvento = eventoService.saveEvento(evento);
    return newEvento;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{idEvento}")
  public ResponseEntity<Evento> getEvento(@PathVariable("idEvento") Integer idEvento) {
    try {
      Evento evento = eventoService.getEvento(idEvento);
      return new ResponseEntity<>(evento, HttpStatus.OK);
    } catch (EventoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.DELETE, RequestMethod.OPTIONS })
  @DeleteMapping("/{idEvento}")
  public ResponseEntity<HttpStatus> deleteEvento(@PathVariable("idEvento") Integer idEvento) {
    try {
      eventoService.deleteEvento(idEvento);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (EventoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
