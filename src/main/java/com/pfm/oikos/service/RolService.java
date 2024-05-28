package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Rol;
import com.pfm.oikos.exception.RolNotFoundException;
import com.pfm.oikos.repository.RolRepository;

@Service
public class RolService {

  @Autowired
  private RolRepository rolRepository;

  public Rol saveRol(Rol rol) {
    return rolRepository.save(rol);
  }

  public Rol getRol(Integer idRol) throws RolNotFoundException {
    return rolRepository.findById(idRol)
      .orElseThrow(() -> new RolNotFoundException("Rol not found with id: " + idRol));
  }

  public void deleteRol(Integer idRol) throws RolNotFoundException {
    if (rolRepository.existsById(idRol)) {
      rolRepository.deleteById(idRol);
    } else {
      throw new RolNotFoundException("Rol not found with id: " + idRol);
    }
  }
}
