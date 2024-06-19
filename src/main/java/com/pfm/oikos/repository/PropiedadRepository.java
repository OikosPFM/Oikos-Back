package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Propiedad;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Integer> {
    Propiedad findByPortalAndNumeroPisoAndLetra(String portal, String numeroPiso, String letra);

}

