package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfm.oikos.entity.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}

