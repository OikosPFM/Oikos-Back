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

import com.pfm.oikos.entity.AsignacionTarea;
import com.pfm.oikos.exception.AsignacionTareaNotFoundException;
import com.pfm.oikos.service.AsignacionTareaService;

@RestController
@RequestMapping(path = "api/v1/asignacionesTarea")
public class AsignacionTareaController {

  @Autowired
  private AsignacionTareaService asignacionTareaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AsignacionTarea createAsignacionTarea(@RequestBody AsignacionTarea asignacionTarea) {
    AsignacionTarea newAsignacionTarea = asignacionTareaService.saveAsignacionTarea(asignacionTarea);
    return newAsignacionTarea;
  }

  @GetMapping("/{idAsignacion}")
  public ResponseEntity<AsignacionTarea> getAsignacionTarea(@PathVariable("idAsignacion") Integer idAsignacion) {
    try {
      AsignacionTarea asignacionTarea = asignacionTareaService.getAsignacionTarea(idAsignacion);
      return new ResponseEntity<>(asignacionTarea, HttpStatus.OK);
    } catch (AsignacionTareaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idAsignacion}")
  public ResponseEntity<HttpStatus> deleteAsignacionTarea(@PathVariable("idAsignacion") Integer idAsignacion) {
    try {
      asignacionTareaService.deleteAsignacionTarea(idAsignacion);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (AsignacionTareaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
