package com.pfm.oikos.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Evento;
import com.pfm.oikos.entity.Tarea;
import com.pfm.oikos.exception.TareaNotFoundException;
import com.pfm.oikos.repository.TareaRepository;

@Service
public class TareaService {

  @Autowired
  private TareaRepository tareaRepository;

  public Tarea saveTarea(Tarea tarea) {
    return tareaRepository.save(tarea);
  }

  public Tarea getTarea(Integer idTarea) throws TareaNotFoundException {
    return tareaRepository.findById(idTarea)
        .orElseThrow(() -> new TareaNotFoundException("Tarea not found with id: " + idTarea));
  }

  public List<Tarea> getAllTareas() {
    return tareaRepository.findAll();
  }

  public Tarea updateTarea(Integer idTarea, Tarea tareaDetails) throws TareaNotFoundException {
    Tarea existingTarea = tareaRepository.findById(idTarea)
        .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada con ID: " + idTarea));

    // Directly update the fields with the provided details
    if (tareaDetails.getInstalacion() != null) {
      existingTarea.setInstalacion(tareaDetails.getInstalacion());
    }
    if (tareaDetails.getNombre() != null) {
      existingTarea.setNombre(tareaDetails.getNombre());
    }
    if (tareaDetails.getDescripcion() != null) {
      existingTarea.setDescripcion(tareaDetails.getDescripcion());
    }
    if (tareaDetails.getFecha() != null) {
      existingTarea.setFecha(tareaDetails.getFecha());
    }
    if (tareaDetails.getDuracion() != null) {
      existingTarea.setDuracion(tareaDetails.getDuracion());
    }
    if (tareaDetails.getUsuarioAsignado() != null) {
      existingTarea.setUsuarioAsignado(tareaDetails.getUsuarioAsignado());
    }
    if (tareaDetails.isTareaAcabada()) {
      existingTarea.setTareaAcabada(tareaDetails.isTareaAcabada());
    }

    return tareaRepository.save(existingTarea);
  }

  public List<Tarea> getTareasByFincaId(Integer fincaId) {
    List<Tarea> tareas = tareaRepository.findAll();
    return tareas.stream()
        .filter(tarea -> tarea.getInstalacion() != null && // Check for null propiedad
            tarea.getInstalacion().getFinca().getIdFinca().equals(fincaId))
        .collect(Collectors.toList());
  }

  public List<Tarea> getTareasByUsuarioId(Integer idUsuario) {
    List<Tarea> tareas = tareaRepository.findAll();
    return tareas.stream().filter(tarea -> tarea.getUsuarioAsignado().getIdUsuario().equals(idUsuario))
        .collect(Collectors.toList());
  }

  public void deleteTarea(Integer idTarea) throws TareaNotFoundException {
    if (tareaRepository.existsById(idTarea)) {
      tareaRepository.deleteById(idTarea);
    } else {
      throw new TareaNotFoundException("Tarea not found with id: " + idTarea);
    }
  }
}
