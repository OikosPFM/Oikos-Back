package com.pfm.oikos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Finca;
import com.pfm.oikos.entity.Instalacion;
import com.pfm.oikos.exception.InstalacionNotFoundException;
import com.pfm.oikos.repository.InstalacionRepository;

@Service
public class InstalacionService {

  @Autowired
  private InstalacionRepository instalacionRepository;

  public Instalacion saveInstalacion(Instalacion instalacion) {
    return instalacionRepository.save(instalacion);
  }

  public Instalacion getInstalacion(Integer idInstalacion) throws InstalacionNotFoundException {
    return instalacionRepository.findById(idInstalacion)
      .orElseThrow(() -> new InstalacionNotFoundException("Instalacion not found with id: " + idInstalacion));
  }

  public void deleteInstalacion(Integer idInstalacion) throws InstalacionNotFoundException {
    if (instalacionRepository.existsById(idInstalacion)) {
      instalacionRepository.deleteById(idInstalacion);
    } else {
      throw new InstalacionNotFoundException("Instalacion not found with id: " + idInstalacion);
    }
  }
  
  public List<Instalacion> getAllInstalaciones() {
      return instalacionRepository.findAll();
  }
}
