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

import com.pfm.oikos.entity.Encuesta;
import com.pfm.oikos.exception.EncuestaNotFoundException;
import com.pfm.oikos.service.EncuestaService;

@RestController
@RequestMapping(path = "api/v1/encuestas")
public class EncuestaController {

  @Autowired
  private EncuestaService encuestaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Encuesta createEncuesta(@RequestBody Encuesta encuesta) {
    Encuesta newEncuesta = encuestaService.saveEncuesta(encuesta);
    return newEncuesta;
  }

  @GetMapping("/{idEncuesta}")
  public ResponseEntity<Encuesta> getEncuesta(@PathVariable("idEncuesta") Integer idEncuesta) {
    try {
      Encuesta encuesta = encuestaService.getEncuesta(idEncuesta);
      return new ResponseEntity<>(encuesta, HttpStatus.OK);
    } catch (EncuestaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idEncuesta}")
  public ResponseEntity<HttpStatus> deleteEncuesta(@PathVariable("idEncuesta") Integer idEncuesta) {
    try {
      encuestaService.deleteEncuesta(idEncuesta);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (EncuestaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

