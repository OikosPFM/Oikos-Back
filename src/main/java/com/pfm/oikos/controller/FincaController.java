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

import com.pfm.oikos.entity.Finca;
import com.pfm.oikos.exception.FincaNotFoundException;
import com.pfm.oikos.service.FincaService;

@RestController
@RequestMapping(path = "api/v1/fincas")
public class FincaController {

  @Autowired
  private FincaService fincaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Finca createFinca(@RequestBody Finca finca) {
    Finca newFinca = fincaService.saveFinca(finca);
    return newFinca;
  }

  @GetMapping("/{idFinca}")
  public ResponseEntity<Finca> getFinca(@PathVariable("idFinca") Integer idFinca) {
    try {
      Finca finca = fincaService.getFinca(idFinca);
      System.out.println(finca);
      return new ResponseEntity<>(finca, HttpStatus.OK);
    } catch (FincaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idFinca}")
  public ResponseEntity<HttpStatus> deleteFinca(@PathVariable("idFinca") Integer idFinca) {
    try {
      fincaService.deleteFinca(idFinca);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (FincaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
