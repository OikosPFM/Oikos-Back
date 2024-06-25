package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.entity.Mensaje;
import com.pfm.oikos.exception.MensajeNotFoundException;
import com.pfm.oikos.repository.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private EntradaForoService entradaForoService;

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
        Mensaje existingMensaje = mensajeRepository.findById(idMensaje)
                .orElseThrow(() -> new MensajeNotFoundException("Mensaje not found with id: " + idMensaje));

        if (mensajeDetails.getUsuario() != null) {
            existingMensaje.setUsuario(mensajeDetails.getUsuario());
        }
        if (mensajeDetails.getEntradaForo() != null) {
            existingMensaje.setEntradaForo(mensajeDetails.getEntradaForo());
        }
        if (mensajeDetails.getCuerpo() != null) {
            existingMensaje.setCuerpo(mensajeDetails.getCuerpo());
        }
        if (mensajeDetails.getTiempo() != null) {
            existingMensaje.setTiempo(mensajeDetails.getTiempo());
        }

        // Save and return the updated mensaje
        return mensajeRepository.save(existingMensaje);
    }

    public void deleteMensaje(int id) {
        mensajeRepository.deleteById(id);
    }

    public List<Mensaje> getMensajesEntrada(String idEntrada) {
        //recuperar entrada por idEntrada
        EntradaForo entradaForo = this.entradaForoService.getEntradaForo(Integer.parseInt(idEntrada));
        //llamar a mensajeRepository.getByEntradaForo
        return this.mensajeRepository.findByEntradaForo(entradaForo);
    }
}
