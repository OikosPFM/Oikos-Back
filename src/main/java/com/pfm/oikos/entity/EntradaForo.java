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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrada_foro")
public class EntradaForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrada;

    @ManyToOne
    @JoinColumn(name = "idHilo")
    private HiloForo hiloForo;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Usuario autor;

    private String nombre;
    private String titulo;
    private String textoComentario;
    private LocalDate fecha;
    private LocalTime hora;

    @OneToMany(mappedBy = "entradaForo")
    private Set<RespuestaForo> respuestas;

    @OneToOne(mappedBy = "entradaForo")
    private Encuesta encuesta;

    // Getters and Setters
    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public HiloForo getHiloForo() {
        return hiloForo;
    }

    public void setHiloForo(HiloForo hiloForo) {
        this.hiloForo = hiloForo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
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

    public Set<RespuestaForo> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Set<RespuestaForo> respuestas) {
        this.respuestas = respuestas;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Object getContenido() {
        throw new UnsupportedOperationException("Unimplemented method 'getContenido'");
    }

    public void setContenido(Object contenido) {
        throw new UnsupportedOperationException("Unimplemented method 'setContenido'");
    }
}

