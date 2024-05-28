package com.pfm.oikos.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hilo_foro")
public class HiloForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHilo;

    @ManyToOne
    @JoinColumn(name = "idCreador")
    private Usuario creador;

    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;

    @OneToMany(mappedBy = "hiloForo")
    private Set<EntradaForo> entradas;

    // Getters and Setters
    public Integer getIdHilo() {
        return idHilo;
    }

    public void setIdHilo(Integer idHilo) {
        this.idHilo = idHilo;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Set<EntradaForo> getEntradas() {
        return entradas;
    }

    public void setEntradas(Set<EntradaForo> entradas) {
        this.entradas = entradas;
    }
}
