package com.pfm.oikos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Usuario;
import com.pfm.oikos.exception.UsuarioNotFoundException;
import com.pfm.oikos.service.UsuarioService;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario createUsuario(@RequestBody Usuario usuario) {
    Usuario newUsuario = usuarioService.saveUsuario(usuario);
    return newUsuario;
  }

  @GetMapping("/{idUsuario}")
  public ResponseEntity<Usuario> getUsuario(@PathVariable("idUsuario") Integer idUsuario) {
    try {
      Usuario usuario = usuarioService.getUsuario(idUsuario);
      return new ResponseEntity<>(usuario, HttpStatus.OK);
    } catch (UsuarioNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idUsuario}")
  public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("idUsuario") Integer idUsuario) {
    try {
      usuarioService.deleteUsuario(idUsuario);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UsuarioNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

