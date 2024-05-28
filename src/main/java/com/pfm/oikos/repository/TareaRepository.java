package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}