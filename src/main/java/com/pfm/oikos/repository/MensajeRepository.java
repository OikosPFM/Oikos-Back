package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    // Métodos de consulta adicionales si es necesario
}
