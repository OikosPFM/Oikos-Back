package com.pfm.oikos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "voto")
@IdClass(VotoId.class)
public class Voto {
    @Id
    private Integer idUsuario;

    @Id
    private Integer idEncuesta;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idEncuesta")
    @JoinColumn(name = "idEncuesta")
    private Encuesta encuesta;

    private Integer opcionElegida;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Integer getOpcionElegida() {
        return opcionElegida;
    }

    public void setOpcionElegida(Integer opcionElegida) {
        this.opcionElegida = opcionElegida;
    }
}
