package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Finca;

// @Repository
// @RepositoryRestResource(path = "fincas", collectionResourceRel = "fincas")
public interface FincaRepository extends JpaRepository<Finca, Integer> {
}
