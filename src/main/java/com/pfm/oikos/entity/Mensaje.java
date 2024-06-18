package com.pfm.oikos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensaje")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_mensaje")
    private Integer idMensaje;

    @Column(name = "ID_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "ID_entrada", nullable = false)
    private Integer idEntrada;

    @Column(name = "cuerpo", nullable = false)
    private String cuerpo;

    @Column(name = "tiempo", nullable = false)
    private LocalDateTime tiempo;

    // Constructor vacío
    public Mensaje() {
    }

    // Constructor con parámetros
    public Mensaje(Integer idUsuario, Integer idEntrada, String cuerpo, LocalDateTime tiempo) {
        this.idUsuario = idUsuario;
        this.idEntrada = idEntrada;
        this.cuerpo = cuerpo;
        this.tiempo = tiempo;
    }

    // Getters and Setters
    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public LocalDateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalDateTime tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "idMensaje=" + idMensaje +
                ", idUsuario=" + idUsuario +
                ", idEntrada=" + idEntrada +
                ", cuerpo='" + cuerpo + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }
}
