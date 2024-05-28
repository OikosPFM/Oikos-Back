package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.EntradaForo;

@Repository
public interface EntradaForoRepository extends JpaRepository<EntradaForo, Integer> {
}
