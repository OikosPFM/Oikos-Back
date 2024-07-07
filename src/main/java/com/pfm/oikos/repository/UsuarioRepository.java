package com.pfm.oikos.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfm.oikos.entity.Usuario;

@Repository
@RepositoryRestResource(path = "usuario", collectionResourceRel = "usuario")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);
}

