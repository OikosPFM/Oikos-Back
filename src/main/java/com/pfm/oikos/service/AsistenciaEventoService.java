package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.AsistenciaEvento;
import com.pfm.oikos.entity.AsistenciaEventoId;
import com.pfm.oikos.exception.AsistenciaEventoNotFoundException;
import com.pfm.oikos.repository.AsistenciaEventoRepository;

@Service
public class AsistenciaEventoService {

  @Autowired
  private AsistenciaEventoRepository asistenciaEventoRepository;

  public AsistenciaEvento saveAsistenciaEvento(AsistenciaEvento asistenciaEvento) {
    return asistenciaEventoRepository.save(asistenciaEvento);
  }

  public void deleteAsistenciaEvento(Integer idUsuario, Integer idEvento) throws AsistenciaEventoNotFoundException {
    AsistenciaEventoId id = new AsistenciaEventoId();
    if (asistenciaEventoRepository.existsById(id)) {
      asistenciaEventoRepository.deleteById(id);
    } else {
      throw new AsistenciaEventoNotFoundException(null);
    }
  }
}
