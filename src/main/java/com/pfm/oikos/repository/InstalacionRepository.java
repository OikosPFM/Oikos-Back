package com.pfm.oikos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Instalacion;

@Repository
public interface InstalacionRepository extends JpaRepository<Instalacion, Integer> {
    List<Instalacion> findByFinca_IdFinca(Integer fincaID);
}

