package com.pfm.oikos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Archivo;
import com.pfm.oikos.service.ArchivoService;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @GetMapping
    public List<Archivo> getAllArchivos() {
        return archivoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivo> getArchivoById(@PathVariable Long id) {
        Optional<Archivo> archivo = archivoService.findById(id);
        if (archivo.isPresent()) {
            return ResponseEntity.ok(archivo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Archivo createArchivo(@RequestBody Archivo archivo) {
        return archivoService.save(archivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Archivo> updateArchivo(@PathVariable Long id, @RequestBody Archivo archivoDetails) {
        Optional<Archivo> archivo = archivoService.findById(id);
        if (archivo.isPresent()) {
            Archivo archivoToUpdate = archivo.get();
            archivoToUpdate.setName(archivoDetails.getName());
            archivoToUpdate.setType(archivoDetails.getType());
            return ResponseEntity.ok(archivoService.save(archivoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArchivo(@PathVariable Long id) {
        Optional<Archivo> archivo = archivoService.findById(id);
        if (archivo.isPresent()) {
            archivoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

