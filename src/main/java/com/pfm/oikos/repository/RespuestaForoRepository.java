package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.RespuestaForo;

@Repository
public interface RespuestaForoRepository extends JpaRepository<RespuestaForo, Integer> {
}