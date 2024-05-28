package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Voto;
import com.pfm.oikos.entity.VotoId;
import com.pfm.oikos.exception.VotoNotFoundException;
import com.pfm.oikos.repository.VotoRepository;

@Service
public class VotoService {

  @Autowired
  private VotoRepository votoRepository;

  public Voto saveVoto(Voto voto) {
    return votoRepository.save(voto);
  }

  public Voto getVoto(Integer idUsuario, Integer idEncuesta) throws VotoNotFoundException {
    VotoId votoId = new VotoId();
    return votoRepository.findById(votoId)
      .orElseThrow(() -> new VotoNotFoundException("Voto not found with idUsuario: " + idUsuario + " and idEncuesta: " + idEncuesta));
  }

  public void deleteVoto(Integer idUsuario, Integer idEncuesta) throws VotoNotFoundException {
    VotoId votoId = new VotoId();
    if (votoRepository.existsById(votoId)) {
      votoRepository.deleteById(votoId);
    } else {
      throw new VotoNotFoundException("Voto not found with idUsuario: " + idUsuario + " and idEncuesta: " + idEncuesta);
    }
  }
}

