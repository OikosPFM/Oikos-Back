package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Reserva;
import com.pfm.oikos.exception.ReservaNotFoundException;
import com.pfm.oikos.repository.ReservaRepository;

@Service
public class ReservaService {

  @Autowired
  private ReservaRepository reservaRepository;

  public Reserva saveReserva(Reserva reserva) {
    return reservaRepository.save(reserva);
  }

  public Reserva getReserva(Integer idReserva) throws ReservaNotFoundException {
    return reservaRepository.findById(idReserva)
      .orElseThrow(() -> new ReservaNotFoundException("Reserva not found with id: " + idReserva));
  }

  public void deleteReserva(Integer idReserva) throws ReservaNotFoundException {
    if (reservaRepository.existsById(idReserva)) {
      reservaRepository.deleteById(idReserva);
    } else {
      throw new ReservaNotFoundException("Reserva not found with id: " + idReserva);
    }
  }
}

