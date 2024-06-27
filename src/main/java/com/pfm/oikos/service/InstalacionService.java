package com.pfm.oikos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<Instalacion> getInstalacionesByFincaID(Integer fincaID) {
    return instalacionRepository.findByFinca_IdFinca(fincaID);
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

  public Instalacion updateInstalacion(Integer idInstalacion, Instalacion instalacionDetails)
      throws InstalacionNotFoundException {
    Instalacion instalacion = instalacionRepository.findById(idInstalacion)
        .orElseThrow(() -> new InstalacionNotFoundException("Instalación no encontrada con ID: " + idInstalacion));

    // Actualiza solo los campos que no son nulos en instalacionDetails
    if (instalacionDetails.getNombre() != null) {
      instalacion.setNombre(instalacionDetails.getNombre());
    }
    if (instalacionDetails.getDiasAbierto() != null) {
      instalacion.setDiasAbierto(instalacionDetails.getDiasAbierto());
    }
    if (instalacionDetails.getHorarioApertura() != null) {
      instalacion.setHorarioApertura(instalacionDetails.getHorarioApertura());
    }
    if (instalacionDetails.getHorarioCierre() != null) {
      instalacion.setHorarioCierre(instalacionDetails.getHorarioCierre());
    }
    if (instalacionDetails.getIntervalo() != null) {
      instalacion.setIntervalo(instalacionDetails.getIntervalo());
    }
    if (instalacionDetails.getPlazasIntervalo() != null) {
      instalacion.setPlazasIntervalo(instalacionDetails.getPlazasIntervalo());
    }
    if (instalacionDetails.getInvitacionesMensualesMaximas() != null) {
      instalacion.setInvitacionesMensualesMaximas(instalacionDetails.getInvitacionesMensualesMaximas());
    }
    // Añade aquí más campos que desees actualizar

    // Guarda y retorna la instalación actualizada
    return instalacionRepository.save(instalacion);
  }

}
