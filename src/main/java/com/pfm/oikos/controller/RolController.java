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

import com.pfm.oikos.entity.Rol;
import com.pfm.oikos.exception.RolNotFoundException;
import com.pfm.oikos.service.RolService;

@RestController
@RequestMapping(path = "api/v1/roles")
public class RolController {

  @Autowired
  private RolService rolService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Rol createRol(@RequestBody Rol rol) {
    Rol newRol = rolService.saveRol(rol);
    return newRol;
  }

  @GetMapping("/{idRol}")
  public ResponseEntity<Rol> getRol(@PathVariable("idRol") Integer idRol) {
    try {
      Rol rol = rolService.getRol(idRol);
      return new ResponseEntity<>(rol, HttpStatus.OK);
    } catch (RolNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idRol}")
  public ResponseEntity<HttpStatus> deleteRol(@PathVariable("idRol") Integer idRol) {
    try {
      rolService.deleteRol(idRol);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RolNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
