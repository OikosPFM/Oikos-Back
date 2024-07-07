package com.pfm.oikos.service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Evento> getEventosByFincaId(Integer fincaId) {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .filter(evento -> evento.getInstalacion() != null && // Check for null propiedad
                        evento.getInstalacion().getFinca().getIdFinca().equals(fincaId))
                .collect(Collectors.toList());
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
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new EventoNotFoundException("Evento no encontrado con ID: " + idEvento));

        // Actualiza solo los campos que no son nulos en eventoDetails
        if (eventoDetails.getTitulo() != null) {
            evento.setTitulo(eventoDetails.getTitulo());
        }
        if (eventoDetails.getFecha() != null) {
            evento.setFecha(eventoDetails.getFecha());
        }
        if (eventoDetails.getHora() != null) {
            evento.setHora(eventoDetails.getHora());
        }
        if (eventoDetails.getDescripcion() != null) {
            evento.setDescripcion(eventoDetails.getDescripcion());
        }
        if (eventoDetails.getCategoria() != null) {
            evento.setCategoria(eventoDetails.getCategoria());
        }
        if (eventoDetails.getParticipantes() != null) {
            evento.setParticipantes(eventoDetails.getParticipantes());
        }
        if (eventoDetails.getAforo() != null) {
            evento.setAforo(eventoDetails.getAforo());
        }
        if (eventoDetails.getInstalacion() != null) {
            evento.setInstalacion(eventoDetails.getInstalacion());
        }
        if (eventoDetails.getOrganizador() != null) {
            evento.setOrganizador(eventoDetails.getOrganizador());
        }
        if (eventoDetails.getAsistentes() != null) {
            evento.setAsistentes(eventoDetails.getAsistentes());
        }

        // Guarda y retorna el evento actualizado
        return eventoRepository.save(evento);
    }

}
