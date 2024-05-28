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

import com.pfm.oikos.entity.RespuestaForo;
import com.pfm.oikos.exception.RespuestaForoNotFoundException;
import com.pfm.oikos.service.RespuestaForoService;

@RestController
@RequestMapping(path = "api/v1/respuestasForo")
public class RespuestaForoController {

  @Autowired
  private RespuestaForoService respuestaForoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RespuestaForo createRespuestaForo(@RequestBody RespuestaForo respuestaForo) {
    RespuestaForo newRespuestaForo = respuestaForoService.saveRespuestaForo(respuestaForo);
    return newRespuestaForo;
  }

  @GetMapping("/{idRespuesta}")
  public ResponseEntity<RespuestaForo> getRespuestaForo(@PathVariable("idRespuesta") Integer idRespuesta) {
    try {
      RespuestaForo respuestaForo = respuestaForoService.getRespuestaForo(idRespuesta);
      return new ResponseEntity<>(respuestaForo, HttpStatus.OK);
    } catch (RespuestaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idRespuesta}")
  public ResponseEntity<HttpStatus> deleteRespuestaForo(@PathVariable("idRespuesta") Integer idRespuesta) {
    try {
      respuestaForoService.deleteRespuestaForo(idRespuesta);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RespuestaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

