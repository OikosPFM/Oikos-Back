package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.repository.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public Mensaje saveMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> getMensajeById(int id) {
        return mensajeRepository.findById(id);
    }

    // public Mensaje createMensaje(Mensaje mensaje) {
    //     return mensajeRepository.save(mensaje);
    // }

    public void deleteMensaje(int id) {
        mensajeRepository.deleteById(id);
    }
}

