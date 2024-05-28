package com.pfm.oikos.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "encuesta")
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEncuesta;

    @OneToOne
    @JoinColumn(name = "idEntrada")
    private EntradaForo entradaForo;

    @ManyToOne
    @JoinColumn(name = "idCreador")
    private Usuario creador;

    private String titulo;
    private String descripcion;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private Integer resultadoOpcion1;
    private Integer resultadoOpcion2;
    private Integer resultadoOpcion3;
    private Integer resultadoOpcion4;

    @OneToMany(mappedBy = "encuesta")
    private Set<Voto> votos;

    // Getters and Setters
    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public EntradaForo getEntradaForo() {
        return entradaForo;
    }

    public void setEntradaForo(EntradaForo entradaForo) {
        this.entradaForo = entradaForo;
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

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public Integer getResultadoOpcion1() {
        return resultadoOpcion1;
    }

    public void setResultadoOpcion1(Integer resultadoOpcion1) {
        this.resultadoOpcion1 = resultadoOpcion1;
    }

    public Integer getResultadoOpcion2() {
        return resultadoOpcion2;
    }

    public void setResultadoOpcion2(Integer resultadoOpcion2) {
        this.resultadoOpcion2 = resultadoOpcion2;
    }

    public Integer getResultadoOpcion3() {
        return resultadoOpcion3;
    }

    public void setResultadoOpcion3(Integer resultadoOpcion3) {
        this.resultadoOpcion3 = resultadoOpcion3;
    }

    public Integer getResultadoOpcion4() {
        return resultadoOpcion4;
    }

    public void setResultadoOpcion4(Integer resultadoOpcion4) {
        this.resultadoOpcion4 = resultadoOpcion4;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }
}

