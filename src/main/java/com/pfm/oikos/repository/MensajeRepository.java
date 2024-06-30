package com.pfm.oikos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.entity.Mensaje;


public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    //@Query("SELECT mensaje FROM mensaje WHERE mensaje.ID_entrada = :idEntrada")
    List<Mensaje> findByEntradaForo(EntradaForo entradaForo);
}

