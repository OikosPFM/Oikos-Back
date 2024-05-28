package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Evento;
import com.pfm.oikos.exception.EventoNotFoundException;
import com.pfm.oikos.repository.EventoRepository;

@Service
public class EventoService {

  @Autowired
  private EventoRepository eventoRepository;

  public Evento saveEvento(Evento evento) {
    return eventoRepository.save(evento);
  }

  public Evento getEvento(Integer idEvento) throws EventoNotFoundException {
    return eventoRepository.findById(idEvento)
      .orElseThrow(() -> new EventoNotFoundException("Evento not found with id: " + idEvento));
  }

  public void deleteEvento(Integer idEvento) throws EventoNotFoundException {
    if (eventoRepository.existsById(idEvento)) {
      eventoRepository.deleteById(idEvento);
    } else {
      throw new EventoNotFoundException("Evento not found with id: " + idEvento);
    }
  }
}

