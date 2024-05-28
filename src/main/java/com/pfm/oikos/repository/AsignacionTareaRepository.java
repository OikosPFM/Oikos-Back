package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.AsignacionTarea;

@Repository
public interface AsignacionTareaRepository extends JpaRepository<AsignacionTarea, Integer> {
}

