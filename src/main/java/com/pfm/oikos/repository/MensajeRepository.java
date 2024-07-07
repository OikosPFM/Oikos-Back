package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfm.oikos.entity.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}

