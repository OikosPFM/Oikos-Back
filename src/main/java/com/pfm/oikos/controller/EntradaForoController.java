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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.exception.EntradaForoNotFoundException;
import com.pfm.oikos.service.EntradaForoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/entradasForo")
public class EntradaForoController {

  @Autowired
  private EntradaForoService entradaForoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntradaForo createEntradaForo(@RequestBody EntradaForo entradaForo) {
    EntradaForo newEntradaForo = entradaForoService.saveEntradaForo(entradaForo);
    return newEntradaForo;
  }

  @GetMapping
  public ResponseEntity<List<EntradaForo>> getEntradasForo() {
    List<EntradaForo> entradasForo = entradaForoService.getAllEntradasForo();
    return new ResponseEntity<>(entradasForo, HttpStatus.OK);
  }

  @GetMapping("/{idEntrada}")
  public ResponseEntity<EntradaForo> getEntradaForo(@PathVariable("idEntrada") Integer idEntrada) {
    try {
      EntradaForo entradaForo = entradaForoService.getEntradaForo(idEntrada);
      return new ResponseEntity<>(entradaForo, HttpStatus.OK);
    } catch (EntradaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // @PutMapping("/{idEntrada}")
  // public ResponseEntity<EntradaForo>
  // updateEntradaForo(@PathVariable("idEntrada") Integer idEntrada,
  // @RequestBody EntradaForo entradaForo) {
  // try {
  // EntradaForo updatedEntradaForo =
  // entradaForoService.updateEntradaForo(idEntrada, entradaForo);
  // return new ResponseEntity<>(updatedEntradaForo, HttpStatus.OK);
  // } catch (EntradaForoNotFoundException exception) {
  // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // }
  // }

  @DeleteMapping("/{idEntrada}")
  public ResponseEntity<HttpStatus> deleteEntradaForo(@PathVariable("idEntrada") Integer idEntrada) {
    try {
      entradaForoService.deleteEntradaForo(idEntrada);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (EntradaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
