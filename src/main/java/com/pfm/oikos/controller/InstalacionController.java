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

import com.pfm.oikos.entity.Instalacion;
import com.pfm.oikos.exception.InstalacionNotFoundException;
import com.pfm.oikos.service.InstalacionService;

@RestController
@RequestMapping(path = "api/v1/instalaciones")
public class InstalacionController {

  @Autowired
  private InstalacionService instalacionService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Instalacion createInstalacion(@RequestBody Instalacion instalacion) {
    Instalacion newInstalacion = instalacionService.saveInstalacion(instalacion);
    return newInstalacion;
  }

  @GetMapping("/{idInstalacion}")
  public ResponseEntity<Instalacion> getInstalacion(@PathVariable("idInstalacion") Integer idInstalacion) {
    try {
      Instalacion instalacion = instalacionService.getInstalacion(idInstalacion);
      return new ResponseEntity<>(instalacion, HttpStatus.OK);
    } catch (InstalacionNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idInstalacion}")
  public ResponseEntity<HttpStatus> deleteInstalacion(@PathVariable("idInstalacion") Integer idInstalacion) {
    try {
      instalacionService.deleteInstalacion(idInstalacion);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (InstalacionNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

