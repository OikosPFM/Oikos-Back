package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.HiloForo;
import com.pfm.oikos.exception.HiloForoNotFoundException;
import com.pfm.oikos.repository.HiloForoRepository;

@Service
public class HiloForoService {

  @Autowired
  private HiloForoRepository hiloForoRepository;

  public HiloForo saveHiloForo(HiloForo hiloForo) {
    return hiloForoRepository.save(hiloForo);
  }

  public HiloForo getHiloForo(Integer idHilo) throws HiloForoNotFoundException {
    return hiloForoRepository.findById(idHilo)
      .orElseThrow(() -> new HiloForoNotFoundException("HiloForo not found with id: " + idHilo));
  }

  public void deleteHiloForo(Integer idHilo) throws HiloForoNotFoundException {
    if (hiloForoRepository.existsById(idHilo)) {
      hiloForoRepository.deleteById(idHilo);
    } else {
      throw new HiloForoNotFoundException("HiloForo not found with id: " + idHilo);
    }
  }
}

