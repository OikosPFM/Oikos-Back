package com.pfm.oikos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Finca;
import com.pfm.oikos.entity.Propiedad;
import com.pfm.oikos.exception.PropiedadNotFoundException;
import com.pfm.oikos.repository.PropiedadRepository;

@Service
public class PropiedadService {

  @Autowired
  private PropiedadRepository propiedadRepository;

  public Propiedad savePropiedad(Propiedad propiedad) {
    return propiedadRepository.save(propiedad);
  }

  public Propiedad getPropiedad(Integer idPropiedad) throws PropiedadNotFoundException {
    return propiedadRepository.findById(idPropiedad)
      .orElseThrow(() -> new PropiedadNotFoundException("Propiedad not found with id: " + idPropiedad));
  }

  public void deletePropiedad(Integer idPropiedad) throws PropiedadNotFoundException {
    if (propiedadRepository.existsById(idPropiedad)) {
      propiedadRepository.deleteById(idPropiedad);
    } else {
      throw new PropiedadNotFoundException("Propiedad not found with id: " + idPropiedad);
    }
  }
  
  public List<Propiedad> getAllPropiedades() {
      return propiedadRepository.findAll();
  }
}
