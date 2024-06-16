package com.pfm.oikos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfm.oikos.entity.Archivo;
import com.pfm.oikos.exception.ArchivoNotFoundException;
import com.pfm.oikos.repository.ArchivoRepository;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    public List<Archivo> findAll() {
        return archivoRepository.findAll();
    }

    public Optional<Archivo> findById(Long id) {
        return archivoRepository.findById(id);
    }

    public Archivo save(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    public void deleteById(Long id) {
        if (!archivoRepository.existsById(id)) {
            throw new ArchivoNotFoundException(id);
        }
        archivoRepository.deleteById(id);
    }
}

