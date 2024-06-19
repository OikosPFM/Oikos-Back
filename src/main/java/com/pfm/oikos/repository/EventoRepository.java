package com.pfm.oikos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByFinca_IdFinca(Integer fincaID);
    

}

