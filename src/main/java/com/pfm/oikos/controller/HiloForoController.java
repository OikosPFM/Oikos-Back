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

import com.pfm.oikos.entity.HiloForo;
import com.pfm.oikos.exception.HiloForoNotFoundException;
import com.pfm.oikos.service.HiloForoService;

@RestController
@RequestMapping(path = "api/v1/hilosForo")
public class HiloForoController {

  @Autowired
  private HiloForoService hiloForoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public HiloForo createHiloForo(@RequestBody HiloForo hiloForo) {
    HiloForo newHiloForo = hiloForoService.saveHiloForo(hiloForo);
    return newHiloForo;
  }

  @GetMapping("/{idHilo}")
  public ResponseEntity<HiloForo> getHiloForo(@PathVariable("idHilo") Integer idHilo) {
    try {
      HiloForo hiloForo = hiloForoService.getHiloForo(idHilo);
      return new ResponseEntity<>(hiloForo, HttpStatus.OK);
    } catch (HiloForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idHilo}")
  public ResponseEntity<HttpStatus> deleteHiloForo(@PathVariable("idHilo") Integer idHilo) {
    try {
      hiloForoService.deleteHiloForo(idHilo);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (HiloForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

