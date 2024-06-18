package com.pfm.oikos.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "finca")
public class Finca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFinca;

    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private String codigoPostal;

    @OneToMany(mappedBy = "finca")
    @JsonManagedReference
    private Set<Propiedad> propiedades;

    @OneToMany(mappedBy = "finca")
    @JsonIgnore
    private Set<Instalacion> instalaciones;

    // Getters and Setters
    public Integer getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(Integer idFinca) {
        this.idFinca = idFinca;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Set<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Set<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public Set<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(Set<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }
}
