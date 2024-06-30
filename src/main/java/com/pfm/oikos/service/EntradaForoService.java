package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.exception.EntradaForoNotFoundException;
import com.pfm.oikos.repository.EntradaForoRepository;
import com.pfm.oikos.repository.FincaRepository;

@Service
public class EntradaForoService {

  @Autowired
  private EntradaForoRepository entradaForoRepository;

  @Autowired
  private FincaRepository fincaRepository;

  public EntradaForo saveEntradaForo(EntradaForo entradaForo) {
    return entradaForoRepository.save(entradaForo);
  }

  public Optional<EntradaForo> getEntradaForoById(int id) {
    return entradaForoRepository.findById(id);
  }

  public EntradaForo getEntradaForo(Integer idEntrada) throws EntradaForoNotFoundException {
    return entradaForoRepository.findById(idEntrada)
        .orElseThrow(() -> new EntradaForoNotFoundException("EntradaForo not found with id: " + idEntrada));
  }

  public List<EntradaForo> getAllEntradasForo() {
    return entradaForoRepository.findAll();
  }


  public EntradaForo updateEntradaForo(Integer idEntrada, EntradaForo entradaForoDetails) throws EntradaForoNotFoundException {
        EntradaForo existingEntradaForo = entradaForoRepository.findById(idEntrada)
                .orElseThrow(() -> new EntradaForoNotFoundException("Mensaje not found with id: " + idEntrada));

        if (entradaForoDetails.getAutor() != null) {
            existingEntradaForo.setAutor(entradaForoDetails.getAutor());
        }
        if (entradaForoDetails.getFinca() != null) {
            existingEntradaForo.setFinca(entradaForoDetails.getFinca());
        }
        if (entradaForoDetails.getTextoComentario() != null) {
            existingEntradaForo.setTextoComentario(entradaForoDetails.getTextoComentario());
        }

        // Save and return the updated mensaje
        return entradaForoRepository.save(existingEntradaForo);
  }

  
//   public EntradaForo updateEntradaForo(Integer idEntrada, EntradaForo entradaForoDetails) throws EntradaForoNotFoundException {
//     Optional<EntradaForo> optionalEntrada = entradaForoRepository.findById(idEntrada);

//     if (optionalEntrada.isPresent()) {
//         EntradaForo existingEntradaForo = optionalEntrada.get();

//         existingEntradaForo.setTitulo(entradaForoDetails.getTitulo());
//         existingEntradaForo.setTextoComentario(entradaForoDetails.getTextoComentario());

//         return entradaForoRepository.save(existingEntradaForo);
//     } else {
//         throw new EntradaForoNotFoundException("Tarea not found with id: " + idEntrada);
//     }
// }

  // public EntradaForo updateEntrada(Integer id, EntradaForo entradaDetails) {
  //   EntradaForo entrada = entradaForoRepository.findById(id)
  //           .orElseThrow(() -> new EntradaForoNotFoundException("Entrada not found for this id :: " + id));

  //   entrada.setTitulo(entradaDetails.getTitulo());
  //   entrada.setTextoComentario(entradaDetails.getTextoComentario());
  //   final EntradaForo updatedEntrada = entradaForoRepository.save(entrada);
  //   return updatedEntrada;
  // }

  public void deleteEntradaForo(Integer idEntrada) throws EntradaForoNotFoundException {
    if (entradaForoRepository.existsById(idEntrada)) {
      entradaForoRepository.deleteById(idEntrada);
    } else {
      throw new EntradaForoNotFoundException("EntradaForo not found with id: " + idEntrada);
    }
  }
}
