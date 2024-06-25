package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.exception.MensajeNotFoundException;
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

    public Mensaje updateMensaje(Integer idMensaje, Mensaje mensajeDetails) throws MensajeNotFoundException {
        Optional<Mensaje> optionalMensaje = mensajeRepository.findById(idMensaje);

        if (optionalMensaje.isPresent()) {
            Mensaje existingMensaje = optionalMensaje.get();

            existingMensaje.setUsuario(mensajeDetails.getUsuario());
            existingMensaje.setEntradaForo(mensajeDetails.getEntradaForo());
            existingMensaje.setCuerpo(mensajeDetails.getCuerpo());
            existingMensaje.setTiempo(mensajeDetails.getTiempo());

            return mensajeRepository.save(existingMensaje);
        } else {
            throw new MensajeNotFoundException("Mensaje not found with id: " + idMensaje);
        }
    }

    public void deleteMensaje(int id) {
        mensajeRepository.deleteById(id);
    }
}
