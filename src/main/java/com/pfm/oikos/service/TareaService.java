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
    Optional<Tarea> optionalTarea = tareaRepository.findById(idTarea);

    if (optionalTarea.isPresent()) {
        Tarea existingTarea = optionalTarea.get();

        // Directly update the fields with the provided details
        existingTarea.setInstalacion(tareaDetails.getInstalacion());
        existingTarea.setNombre(tareaDetails.getNombre());
        existingTarea.setDescripcion(tareaDetails.getDescripcion());
        existingTarea.setFecha(tareaDetails.getFecha());
        existingTarea.setDuracion(tareaDetails.getDuracion());
        existingTarea.setUsuarioAsignado(tareaDetails.getUsuarioAsignado());
        existingTarea.setTareaAcabada(tareaDetails.isTareaAcabada());

        return tareaRepository.save(existingTarea);
    } else {
        throw new TareaNotFoundException("Tarea not found with id: " + idTarea);
    }
}

  public void deleteTarea(Integer idTarea) throws TareaNotFoundException {
    if (tareaRepository.existsById(idTarea)) {
      tareaRepository.deleteById(idTarea);
    } else {
      throw new TareaNotFoundException("Tarea not found with id: " + idTarea);
    }
  }
}

