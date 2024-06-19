package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Evento;
import com.pfm.oikos.entity.Instalacion;
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

  public List<Evento> getEventosByFincaID(Integer fincaID) {
      return eventoRepository.findByFinca_IdFinca(fincaID);
  }
  
  public void deleteEvento(Integer idEvento) throws EventoNotFoundException {
    if (eventoRepository.existsById(idEvento)) {
      eventoRepository.deleteById(idEvento);
    } else {
      throw new EventoNotFoundException("Evento not found with id: " + idEvento);
    }
  }
  
  public List<Evento> getAllEventos() {
      return eventoRepository.findAll();
  }
  
  
  
  public Evento updateEvento(Integer idEvento, Evento eventoDetails) throws EventoNotFoundException {
      Optional<Evento> optionalEvento = eventoRepository.findById(idEvento);

      if (optionalEvento.isPresent()) {
          Evento existingEvento = optionalEvento.get();

          // Update only the provided fields
          if (eventoDetails.getInstalacion() != null) {
              existingEvento.setInstalacion(eventoDetails.getInstalacion());
          }
          if (eventoDetails.getOrganizador() != null) {
              existingEvento.setOrganizador(eventoDetails.getOrganizador());
          }
          if (eventoDetails.getTitulo() != null) {
              existingEvento.setTitulo(eventoDetails.getTitulo());
          }
          if (eventoDetails.getFecha() != null) {
              existingEvento.setFecha(eventoDetails.getFecha());
          }
          if (eventoDetails.getHora() != null) {
              existingEvento.setHora(eventoDetails.getHora());
          }
          if (eventoDetails.getDescripcion() != null) {
              existingEvento.setDescripcion(eventoDetails.getDescripcion());
          }
          if (eventoDetails.getCategoria() != null) {
              existingEvento.setCategoria(eventoDetails.getCategoria());
          }
          if (eventoDetails.getParticipantes() != null) {
              existingEvento.setParticipantes(eventoDetails.getParticipantes());
          }
          if (eventoDetails.getAforo() != null) {
              existingEvento.setAforo(eventoDetails.getAforo());
          }
          if (eventoDetails.getAsistentes() != null) {
              existingEvento.setAsistentes(eventoDetails.getAsistentes());
          }

          return eventoRepository.save(existingEvento);
      } else {
          throw new EventoNotFoundException("Evento not found with id: " + idEvento);
      }
  }
  
}

