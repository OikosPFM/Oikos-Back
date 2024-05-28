package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Encuesta;
import com.pfm.oikos.exception.EncuestaNotFoundException;
import com.pfm.oikos.repository.EncuestaRepository;

@Service
public class EncuestaService {

  @Autowired
  private EncuestaRepository encuestaRepository;

  public Encuesta saveEncuesta(Encuesta encuesta) {
    return encuestaRepository.save(encuesta);
  }

  public Encuesta getEncuesta(Integer idEncuesta) throws EncuestaNotFoundException {
    return encuestaRepository.findById(idEncuesta)
      .orElseThrow(() -> new EncuestaNotFoundException("Encuesta not found with id: " + idEncuesta));
  }

  public void deleteEncuesta(Integer idEncuesta) throws EncuestaNotFoundException {
    if (encuestaRepository.existsById(idEncuesta)) {
      encuestaRepository.deleteById(idEncuesta);
    } else {
      throw new EncuestaNotFoundException("Encuesta not found with id: " + idEncuesta);
    }
  }
}
