package com.pfm.oikos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Voto;
import com.pfm.oikos.exception.VotoNotFoundException;
import com.pfm.oikos.service.VotoService;

@RestController
@RequestMapping(path = "api/v1/votos")
public class VotoController {

  @Autowired
  private VotoService votoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Voto createVoto(@RequestBody Voto voto) {
    Voto newVoto = votoService.saveVoto(voto);
    return newVoto;
  }

  @GetMapping("/{idUsuario}/{idEncuesta}")
  public ResponseEntity<Voto> getVoto(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idEncuesta") Integer idEncuesta) {
    try {
      Voto voto = votoService.getVoto(idUsuario, idEncuesta);
      return new ResponseEntity<>(voto, HttpStatus.OK);
    } catch (VotoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idUsuario}/{idEncuesta}")
  public ResponseEntity<HttpStatus> deleteVoto(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idEncuesta") Integer idEncuesta) {
    try {
      votoService.deleteVoto(idUsuario, idEncuesta);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (VotoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

