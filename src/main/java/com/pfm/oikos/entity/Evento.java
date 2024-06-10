package com.pfm.oikos.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @ManyToOne
    @JsonBackReference 
    @JoinColumn(name = "idInstalacion")
    private Instalacion instalacion;

    @ManyToOne
    @JoinColumn(name = "idOrganizador")
    private Usuario organizador;

    private String titulo;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer participantes;
    private Integer aforo;

    @ManyToMany
    @JoinTable(
        name = "asistencia_evento",
        joinColumns = @JoinColumn(name = "idEvento"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario")
    )
    private Set<Usuario> asistentes;

    // Getters and Setters
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Integer participantes) {
        this.participantes = participantes;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Set<Usuario> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Set<Usuario> asistentes) {
        this.asistentes = asistentes;
    }
}

enum Categoria {
    Fiesta, Junta, Excursion, Otros
}
