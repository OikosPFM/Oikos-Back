package com.pfm.oikos.entity;

import java.io.Serializable;

public class VotoId implements Serializable {
    private Integer idUsuario;
    private Integer idEncuesta;

    
    // Getters and Setters

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    // Equals and HashCode methods

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     VotoId votoId = (VotoId) o;
    //     return Objects.equals(idUsuario, votoId.idUsuario) &&
    //             Objects.equals(idEncuesta, votoId.idEncuesta);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(idUsuario, idEncuesta);
    // }
}
