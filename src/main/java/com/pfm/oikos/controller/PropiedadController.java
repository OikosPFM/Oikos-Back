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

import com.pfm.oikos.entity.Propiedad;
import com.pfm.oikos.exception.PropiedadNotFoundException;
import com.pfm.oikos.service.PropiedadService;

@RestController
@RequestMapping(path = "api/v1/propiedades")
public class PropiedadController {

  @Autowired
  private PropiedadService propiedadService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Propiedad createPropiedad(@RequestBody Propiedad propiedad) {
    Propiedad newPropiedad = propiedadService.savePropiedad(propiedad);
    return newPropiedad;
  }

  @GetMapping("/{idPropiedad}")
  public ResponseEntity<Propiedad> getPropiedad(@PathVariable("idPropiedad") Integer idPropiedad) {
    try {
      Propiedad propiedad = propiedadService.getPropiedad(idPropiedad);
      return new ResponseEntity<>(propiedad, HttpStatus.OK);
    } catch (PropiedadNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idPropiedad}")
  public ResponseEntity<HttpStatus> deletePropiedad(@PathVariable("idPropiedad") Integer idPropiedad) {
    try {
      propiedadService.deletePropiedad(idPropiedad);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (PropiedadNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
