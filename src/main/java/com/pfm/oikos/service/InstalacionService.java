package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

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
  
  public Instalacion updateInstalacion(Integer idInstalacion, Instalacion instalacionDetails) throws InstalacionNotFoundException {
	    Optional<Instalacion> optionalInstalacion = instalacionRepository.findById(idInstalacion);

	    if (optionalInstalacion.isPresent()) {
	        Instalacion existingInstalacion = optionalInstalacion.get();

	        // Update only the provided fields
	        if (instalacionDetails.getFinca() != null) {
	            existingInstalacion.setFinca(instalacionDetails.getFinca());
	        }
	        if (instalacionDetails.getNombre() != null) {
	            existingInstalacion.setNombre(instalacionDetails.getNombre());
	        }
	        if (instalacionDetails.getDiasAbierto() != null) {
	            existingInstalacion.setDiasAbierto(instalacionDetails.getDiasAbierto());
	        }
	        if (instalacionDetails.getHorarioApertura() != null) {
	            existingInstalacion.setHorarioApertura(instalacionDetails.getHorarioApertura());
	        }
	        if (instalacionDetails.getHorarioCierre() != null) {
	            existingInstalacion.setHorarioCierre(instalacionDetails.getHorarioCierre());
	        }
	        if (instalacionDetails.getIntervalo() != null) {
	            existingInstalacion.setIntervalo(instalacionDetails.getIntervalo());
	        }
	        if (instalacionDetails.getPlazasIntervalo() != null) {
	            existingInstalacion.setPlazasIntervalo(instalacionDetails.getPlazasIntervalo());
	        }
	        if (instalacionDetails.getInvitacionesMensualesMaximas() != null) {
	            existingInstalacion.setInvitacionesMensualesMaximas(instalacionDetails.getInvitacionesMensualesMaximas());
	        }

	        return instalacionRepository.save(existingInstalacion);
	    } else {
	        throw new InstalacionNotFoundException("Instalacion not found with id: " + idInstalacion);
	    }
	}
}
