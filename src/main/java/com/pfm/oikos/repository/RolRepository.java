package com.pfm.oikos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}

