package com.pfm.oikos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.Finca;
import com.pfm.oikos.entity.Propiedad;
import com.pfm.oikos.exception.PropiedadNotFoundException;
import com.pfm.oikos.repository.PropiedadRepository;
import com.pfm.oikos.service.FincaService;
import com.pfm.oikos.service.PropiedadService;

@RestController
@RequestMapping(path = "api/v1/propiedades")
public class PropiedadController {

  @Autowired
  private PropiedadService propiedadService;
  

  
  @Autowired
  private FincaService fincaService;


  /*@CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Propiedad createPropiedad(@RequestBody Propiedad propiedad) {
    Propiedad newPropiedad = propiedadService.savePropiedad(propiedad);
    return newPropiedad;
  }*/

  @GetMapping("/{idPropiedad}")
  public ResponseEntity<Propiedad> getPropiedad(@PathVariable("idPropiedad") Integer idPropiedad) {
    try {
      Propiedad propiedad = propiedadService.getPropiedad(idPropiedad);
      return new ResponseEntity<>(propiedad, HttpStatus.OK);
    } catch (PropiedadNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{idPropiedad}")
  public ResponseEntity<HttpStatus> deletePropiedad(@PathVariable("idPropiedad") Integer idPropiedad) {
    try {
      propiedadService.deletePropiedad(idPropiedad);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (PropiedadNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public ResponseEntity<List<Propiedad>> getAllFincas() {
      List<Propiedad> propiedades = propiedadService.getAllPropiedades();
      return new ResponseEntity<>(propiedades, HttpStatus.OK);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<Propiedad> buscarOCrearPropiedad(@RequestBody Propiedad propiedad) {
      Finca finca = fincaService.getFinca(propiedad.getFinca().getIdFinca());
      if (finca == null) {
          return ResponseEntity.notFound().build();
      }

      // Aquí podrías tener lógica adicional para buscar o crear la propiedad
      // dependiendo de los datos recibidos y la existencia en la base de datos

      // Ejemplo: buscar propiedad existente o crear una nueva
      Propiedad propiedadExistente = propiedadService.findByPortalPisoLetra(propiedad.getPortal(), propiedad.getNumeroPiso(), propiedad.getLetra());
      if (propiedadExistente != null) {
          return ResponseEntity.ok(propiedadExistente);
      } else {
          propiedad.setFinca(finca); // Asignar la finca a la propiedad si no existe
          Propiedad nuevaPropiedad = propiedadService.savePropiedad(propiedad);
          return ResponseEntity.ok(nuevaPropiedad);
      }
  }
}
