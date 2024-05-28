package com.pfm.oikos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Voto;
import com.pfm.oikos.entity.VotoId;

@Repository
@RepositoryRestResource(path = "votos", collectionResourceRel = "votos")
public interface VotoRepository extends JpaRepository<Voto, VotoId> {
}

