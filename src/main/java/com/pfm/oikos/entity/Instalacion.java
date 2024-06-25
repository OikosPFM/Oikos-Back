package com.pfm.oikos.entity;

import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "instalacion")
public class Instalacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstalacion;

    @ManyToOne
    @JoinColumn(name = "idFinca")
    private Finca finca;

    private String nombre;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "dias_abierto", joinColumns = @JoinColumn(name = "id_instalacion"))
    @Column(name = "dia")
    @Enumerated(EnumType.STRING)
    private Set<String> diasAbierto;

    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private LocalTime intervalo;
    private Integer plazasIntervalo;
    private Integer invitacionesMensualesMaximas;

    @OneToMany(mappedBy = "instalacion")
    @JsonIgnore
    private Set<Reserva> reservas;

    @OneToMany(mappedBy = "instalacion")
    @JsonIgnore
    private Set<Tarea> tareas;

    @OneToMany(mappedBy = "instalacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Evento> eventos;

    // Getters and Setters
    public Integer getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(Integer idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getDiasAbierto() {
        return diasAbierto;
    }

    public void setDiasAbierto(Set<String> diasAbierto) {
        this.diasAbierto = diasAbierto;
    }

    public LocalTime getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(LocalTime horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public LocalTime getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(LocalTime horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public LocalTime getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(LocalTime intervalo) {
        this.intervalo = intervalo;
    }

    public Integer getPlazasIntervalo() {
        return plazasIntervalo;
    }

    public void setPlazasIntervalo(Integer plazasIntervalo) {
        this.plazasIntervalo = plazasIntervalo;
    }

    public Integer getInvitacionesMensualesMaximas() {
        return invitacionesMensualesMaximas;
    }

    public void setInvitacionesMensualesMaximas(Integer invitacionesMensualesMaximas) {
        this.invitacionesMensualesMaximas = invitacionesMensualesMaximas;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }
}
