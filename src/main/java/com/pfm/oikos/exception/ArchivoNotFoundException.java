package com.pfm.oikos.exception;

public class ArchivoNotFoundException extends RuntimeException {
    public ArchivoNotFoundException(Long id) {
        super("Archivo no encontrado con id: " + id);
    }
}

