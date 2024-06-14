package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

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

  public EntradaForo updateEntradaForo(Integer idEntrada, EntradaForo entradaDetails) throws EntradaForoNotFoundException {
	    Optional<EntradaForo> optionalEntrada = entradaForoRepository.findById(idEntrada);

	    if (optionalEntrada.isPresent()) {
	        EntradaForo existingEntrada = optionalEntrada.get();

	        // Update only the provided fields
	        if (entradaDetails.getIdEntrada() != null) {
	            existingEntrada.setIdEntrada(entradaDetails.getIdEntrada());
	        }
	        if (entradaDetails.getAutor() != null) {
	            existingEntrada.setAutor(entradaDetails.getAutor());
	        }
	        if (entradaDetails.getTitulo() != null) {
	            existingEntrada.setTitulo(entradaDetails.getTitulo());
	        }
          if (entradaDetails.getTextoComentario() != null) {
            existingEntrada.setTextoComentario(entradaDetails.getTextoComentario());
          }
          if (entradaDetails.getFecha() != null) {
              existingEntrada.setFecha(entradaDetails.getFecha());
          }
          if (entradaDetails.getHora() != null) {
              existingEntrada.setHora(entradaDetails.getHora());
          }

	        return entradaForoRepository.save(existingEntrada);
	    } else {
	        throw new EntradaForoNotFoundException("Entrada not found with id: " + idEntrada);
	    }
  }

  public void deleteEntradaForo(Integer idEntrada) throws EntradaForoNotFoundException {
    if (entradaForoRepository.existsById(idEntrada)) {
      entradaForoRepository.deleteById(idEntrada);
    } else {
      throw new EntradaForoNotFoundException("EntradaForo not found with id: " + idEntrada);
    }
  }

  public List<EntradaForo> getAllEntradasForo() {
    return entradaForoRepository.findAll();
  }

  // public Page<EntradaForo> getEntradasPaginadas(int page, int size) {
  //     PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaCreacion"));
  //     return EntradaForoRepository.findAll(pageRequest);
  // }
}
