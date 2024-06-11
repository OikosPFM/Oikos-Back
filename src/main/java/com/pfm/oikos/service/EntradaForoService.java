package com.pfm.oikos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.exception.EntradaForoNotFoundException;
import com.pfm.oikos.repository.EntradaForoRepository;

@Service
public class EntradaForoService {

  @Autowired
  private EntradaForoRepository entradaForoRepository;

  public EntradaForo saveEntradaForo(EntradaForo entradaForo) {
    return entradaForoRepository.save(entradaForo);
  }

  public EntradaForo getEntradaForo(Integer idEntrada) throws EntradaForoNotFoundException {
    return entradaForoRepository.findById(idEntrada)
        .orElseThrow(() -> new EntradaForoNotFoundException("EntradaForo not found with id: " + idEntrada));
  }

  public List<EntradaForo> getAllEntradasForo() {
    return entradaForoRepository.findAll();
  }

  public void deleteEntradaForo(Integer idEntrada) throws EntradaForoNotFoundException {
    if (entradaForoRepository.existsById(idEntrada)) {
      entradaForoRepository.deleteById(idEntrada);
    } else {
      throw new EntradaForoNotFoundException("EntradaForo not found with id: " + idEntrada);
    }
  }

  // public EntradaForo updateEntradaForo(Integer idEntrada, EntradaForo
  // nuevaEntradaForo) throws EntradaForoNotFoundException {
  // return entradaForoRepository.findById(idEntrada).map(entradaForo -> {
  // entradaForo.setTitulo(nuevaEntradaForo.getTitulo());
  // entradaForo.setContenido(nuevaEntradaForo.getContenido());
  // return entradaForoRepository.save(entradaForo);
  // }).orElseThrow(() -> new EntradaForoNotFoundException("EntradaForo not found
  // with id: " + idEntrada));
  // }
}
