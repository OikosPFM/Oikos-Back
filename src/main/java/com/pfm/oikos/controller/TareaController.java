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

import com.pfm.oikos.entity.Tarea;
import com.pfm.oikos.exception.TareaNotFoundException;
import com.pfm.oikos.service.TareaService;

@RestController
@RequestMapping(path = "api/v1/tareas")
public class TareaController {

  @Autowired
  private TareaService tareaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Tarea createTarea(@RequestBody Tarea tarea) {
    Tarea newTarea = tareaService.saveTarea(tarea);
    return newTarea;
  }

  @GetMapping("/{idTarea}")
  public ResponseEntity<Tarea> getTarea(@PathVariable("idTarea") Integer idTarea) {
    try {
      Tarea tarea = tareaService.getTarea(idTarea);
      return new ResponseEntity<>(tarea, HttpStatus.OK);
    } catch (TareaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idTarea}")
  public ResponseEntity<HttpStatus> deleteTarea(@PathVariable("idTarea") Integer idTarea) {
    try {
      tareaService.deleteTarea(idTarea);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (TareaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

