package com.pfm.oikos.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.service.MensajeService;


@RestController
@CrossOrigin(origins = "*")
// @RequestMapping("/mensajes")
@RequestMapping(path = "api/v1/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mensaje createMensajeForo(@RequestBody Mensaje mensaje) {
        Mensaje newMensaje = mensajeService.saveMensaje(mensaje);
        return newMensaje;
    }

    @GetMapping
    public List<Mensaje> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }

    @GetMapping("entrada/{idEntrada}")
    public List<Mensaje> getMensajesEntrada(@RequestParam String idEntrada) {
        return mensajeService.getMensajesEntrada(idEntrada);
        // return new String();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable int id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        if (mensaje.isPresent()) {
            Mensaje mensajeFound = mensaje.get();
            // Aquí puedes verificar si el Usuario asociado al mensaje no es null antes de devolverlo
            if (mensajeFound.getUsuario() != null) {
                return ResponseEntity.ok(mensajeFound);
            } else {
                // Manejo del caso donde usuario es null, por ejemplo, devolver un código de error apropiado
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping
    // public Mensaje createMensaje(@RequestBody Mensaje mensaje) {
    //     // Aquí puedes verificar si el Usuario en el mensaje no es null antes de guardarlo
    //     if (mensaje.getUsuario() == null) {
    //         // Manejo del caso donde usuario es null, por ejemplo, lanzar una excepción o manejarlo de otra manera adecuada para tu aplicación
    //         throw new IllegalArgumentException("El mensaje debe tener un usuario asociado.");
    //     }
    //     return mensajeService.createMensaje(mensaje);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable int id) {
        mensajeService.deleteMensaje(id);
        return ResponseEntity.noContent().build();
    }
}

