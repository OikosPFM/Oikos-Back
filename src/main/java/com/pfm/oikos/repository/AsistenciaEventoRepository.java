package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.AsistenciaEvento;
import com.pfm.oikos.entity.AsistenciaEventoId;

@Repository
public interface AsistenciaEventoRepository extends JpaRepository<AsistenciaEvento, AsistenciaEventoId> {
}

