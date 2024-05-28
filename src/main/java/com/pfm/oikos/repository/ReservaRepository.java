package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}

