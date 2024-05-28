package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Instalacion;

@Repository
public interface InstalacionRepository extends JpaRepository<Instalacion, Integer> {
}

