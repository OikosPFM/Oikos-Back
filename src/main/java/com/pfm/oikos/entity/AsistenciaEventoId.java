package com.pfm.oikos.entity;

import java.io.Serializable;

public class AsistenciaEventoId implements Serializable {
    private Integer idUsuario;
    private Integer idEvento;

        // Getters and Setters

        public Integer getIdUsuario() {
            return idUsuario;
        }
    
        public void setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
        }
    
        public Integer getIdEvento() {
            return idEvento;
        }
    
        public void setIdEvento(Integer idEvento) {
            this.idEvento = idEvento;
        }
    
        // Equals and HashCode methods
    
        // @Override
        // public boolean equals(Object o) {
        //     if (this == o) return true;
        //     if (o == null || getClass() != o.getClass()) return false;
        //     AsistenciaEventoId that = (AsistenciaEventoId) o;
        //     return Objects.equals(idUsuario, that.idUsuario) &&
        //             Objects.equals(idEvento, that.idEvento);
        // }
    
        // @Override
        // public int hashCode() {
        //     return Objects.hash(idUsuario, idEvento);
        // }
}