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

import com.pfm.oikos.entity.Reserva;
import com.pfm.oikos.exception.ReservaNotFoundException;
import com.pfm.oikos.service.ReservaService;

@RestController
@RequestMapping(path = "api/v1/reservas")
public class ReservaController {

  @Autowired
  private ReservaService reservaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Reserva createReserva(@RequestBody Reserva reserva) {
    Reserva newReserva = reservaService.saveReserva(reserva);
    return newReserva;
  }

  @GetMapping("/{idReserva}")
  public ResponseEntity<Reserva> getReserva(@PathVariable("idReserva") Integer idReserva) {
    try {
      Reserva reserva = reservaService.getReserva(idReserva);
      return new ResponseEntity<>(reserva, HttpStatus.OK);
    } catch (ReservaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idReserva}")
  public ResponseEntity<HttpStatus> deleteReserva(@PathVariable("idReserva") Integer idReserva) {
    try {
      reservaService.deleteReserva(idReserva);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (ReservaNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

