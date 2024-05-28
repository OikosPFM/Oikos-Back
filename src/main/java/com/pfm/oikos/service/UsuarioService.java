package com.pfm.oikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Usuario;
import com.pfm.oikos.exception.UsuarioNotFoundException;
import com.pfm.oikos.repository.UsuarioRepository;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario saveUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Usuario getUsuario(Integer idUsuario) throws UsuarioNotFoundException {
    return usuarioRepository.findById(idUsuario)
      .orElseThrow(() -> new UsuarioNotFoundException("Usuario not found with id: " + idUsuario));
  }

  public void deleteUsuario(Integer idUsuario) throws UsuarioNotFoundException {
    if (usuarioRepository.existsById(idUsuario)) {
      usuarioRepository.deleteById(idUsuario);
    } else {
      throw new UsuarioNotFoundException("Usuario not found with id: " + idUsuario);
    }
  }
}

