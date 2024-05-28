package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.RespuestaForo;
import com.pfm.oikos.exception.RespuestaForoNotFoundException;
import com.pfm.oikos.repository.RespuestaForoRepository;

@Service
public class RespuestaForoService {

  @Autowired
  private RespuestaForoRepository respuestaForoRepository;

  public RespuestaForo saveRespuestaForo(RespuestaForo respuestaForo) {
    return respuestaForoRepository.save(respuestaForo);
  }

  public RespuestaForo getRespuestaForo(Integer idRespuesta) throws RespuestaForoNotFoundException {
    return respuestaForoRepository.findById(idRespuesta)
      .orElseThrow(() -> new RespuestaForoNotFoundException("RespuestaForo not found with id: " + idRespuesta));
  }

  public void deleteRespuestaForo(Integer idRespuesta) throws RespuestaForoNotFoundException {
    if (respuestaForoRepository.existsById(idRespuesta)) {
      respuestaForoRepository.deleteById(idRespuesta);
    } else {
      throw new RespuestaForoNotFoundException("RespuestaForo not found with id: " + idRespuesta);
    }
  }
}

