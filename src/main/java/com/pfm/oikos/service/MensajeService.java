package com.pfm.oikos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.exception.MensajeNotFoundException;
import com.pfm.oikos.repository.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    public Mensaje getMensajeById(Integer id) {
        return mensajeRepository.findById(id).orElseThrow(() -> new MensajeNotFoundException("Mensaje no encontrado con ID: " + id));
    }

    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public Mensaje updateMensaje(Integer id, Mensaje mensajeDetails) {
        Mensaje mensaje = mensajeRepository.findById(id).orElseThrow(() -> new MensajeNotFoundException("Mensaje no encontrado con ID: " + id));

        mensaje.setIdUsuario(mensajeDetails.getIdUsuario());
        mensaje.setIdEntrada(mensajeDetails.getIdEntrada());
        mensaje.setCuerpo(mensajeDetails.getCuerpo());
        mensaje.setTiempo(mensajeDetails.getTiempo());

        return mensajeRepository.save(mensaje);
    }

    public void deleteMensaje(Integer id) {
        Mensaje mensaje = mensajeRepository.findById(id).orElseThrow(() -> new MensajeNotFoundException("Mensaje no encontrado con ID: " + id));
        mensajeRepository.delete(mensaje);
    }
}

