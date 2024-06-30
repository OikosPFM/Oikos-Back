package com.pfm.oikos.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public void deleteTarea(Integer idTarea) throws TareaNotFoundException {
    if (tareaRepository.existsById(idTarea)) {
      tareaRepository.deleteById(idTarea);
    } else {
      throw new TareaNotFoundException("Tarea not found with id: " + idTarea);
    }
  }

  public Tarea updateTareaEstado(Integer idTarea) throws TareaNotFoundException {
    Tarea tarea = tareaRepository.findById(idTarea)
        .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrado con ID: " + idTarea));
    boolean otroEstado = !tarea.isTareaAcabada();
    tarea.setTareaAcabada(otroEstado);

    return tareaRepository.save(tarea);
  }
}
