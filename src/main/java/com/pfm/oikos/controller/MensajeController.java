package com.pfm.oikos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.service.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public List<Mensaje> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Integer id) {
        Mensaje mensaje = mensajeService.getMensajeById(id);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public Mensaje createMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.createMensaje(mensaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> updateMensaje(@PathVariable Integer id, @RequestBody Mensaje mensajeDetails) {
        Mensaje updatedMensaje = mensajeService.updateMensaje(id, mensajeDetails);
        return ResponseEntity.ok(updatedMensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Integer id) {
        mensajeService.deleteMensaje(id);
        return ResponseEntity.noContent().build();
    }
}

