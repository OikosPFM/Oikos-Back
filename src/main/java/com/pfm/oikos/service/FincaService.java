package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Finca;
import com.pfm.oikos.exception.FincaNotFoundException;
import com.pfm.oikos.repository.FincaRepository;

@Service
public class FincaService {

  @Autowired
  FincaRepository fincaRepository;

  public Finca saveFinca(Finca finca) {
    return fincaRepository.save(finca);
  }

  public Finca getFinca(Integer idFinca) throws FincaNotFoundException {
    return fincaRepository.findById(idFinca)
      .orElseThrow(() -> new FincaNotFoundException("Finca not found with id: " + idFinca));
  }

  public void deleteFinca(Integer idFinca) throws FincaNotFoundException {
    if (fincaRepository.existsById(idFinca)) {
      fincaRepository.deleteById(idFinca);
    } else {
      throw new FincaNotFoundException("Finca not found with id: " + idFinca);
    }
  }
}
