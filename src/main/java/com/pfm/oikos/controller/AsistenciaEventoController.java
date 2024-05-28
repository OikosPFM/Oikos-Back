package com.pfm.oikos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.AsistenciaEvento;
import com.pfm.oikos.exception.AsistenciaEventoNotFoundException;
import com.pfm.oikos.service.AsistenciaEventoService;

@RestController
@RequestMapping(path = "api/v1/asistenciaEventos")
public class AsistenciaEventoController {

  @Autowired
  private AsistenciaEventoService asistenciaEventoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AsistenciaEvento createAsistenciaEvento(@RequestBody AsistenciaEvento asistenciaEvento) {
    AsistenciaEvento newAsistenciaEvento = asistenciaEventoService.saveAsistenciaEvento(asistenciaEvento);
    return newAsistenciaEvento;
  }

  @DeleteMapping("/{idUsuario}/{idEvento}")
  public ResponseEntity<HttpStatus> deleteAsistenciaEvento(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idEvento") Integer idEvento) {
    try {
      asistenciaEventoService.deleteAsistenciaEvento(idUsuario, idEvento);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (AsistenciaEventoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
