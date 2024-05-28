package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.HiloForo;

@Repository
public interface HiloForoRepository extends JpaRepository<HiloForo, Integer> {
}
