package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.AsignacionTarea;
import com.pfm.oikos.exception.AsignacionTareaNotFoundException;
import com.pfm.oikos.repository.AsignacionTareaRepository;

@Service
public class AsignacionTareaService {

  @Autowired
  private AsignacionTareaRepository asignacionTareaRepository;

  public AsignacionTarea saveAsignacionTarea(AsignacionTarea asignacionTarea) {
    return asignacionTareaRepository.save(asignacionTarea);
  }

  public AsignacionTarea getAsignacionTarea(Integer idAsignacion) throws AsignacionTareaNotFoundException {
    return asignacionTareaRepository.findById(idAsignacion)
      .orElseThrow(() -> new AsignacionTareaNotFoundException("AsignacionTarea not found with id: " + idAsignacion));
  }

  public void deleteAsignacionTarea(Integer idAsignacion) throws AsignacionTareaNotFoundException {
    if (asignacionTareaRepository.existsById(idAsignacion)) {
      asignacionTareaRepository.deleteById(idAsignacion);
    } else {
      throw new AsignacionTareaNotFoundException("AsignacionTarea not found with id: " + idAsignacion);
    }
  }
}

