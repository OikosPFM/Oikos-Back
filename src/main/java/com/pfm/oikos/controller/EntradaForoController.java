package com.pfm.oikos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfm.oikos.entity.EntradaForo;
import com.pfm.oikos.exception.EntradaForoNotFoundException;
import com.pfm.oikos.service.EntradaForoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/entradasForo")
public class EntradaForoController {

  @Autowired
  private EntradaForoService entradaForoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntradaForo createEntradaForo(@RequestBody EntradaForo entradaForo) {
    EntradaForo newEntradaForo = entradaForoService.saveEntradaForo(entradaForo);
    return newEntradaForo;
  }
  
  // @GetMapping
  //   public List<Mensaje> getAllEntradasForo() {
  //     return entradaForoService.getAllEntradasForo();
  // }

  @GetMapping
  public ResponseEntity<List<EntradaForo>> getEntradasForo() {
    List<EntradaForo> entradasForo = entradaForoService.getAllEntradasForo();
    return new ResponseEntity<>(entradasForo, HttpStatus.OK);
  }

  @GetMapping("/{idEntrada}")
  public ResponseEntity<EntradaForo> getEntradaForo(@PathVariable("idEntrada") Integer idEntrada) {
    try {
      EntradaForo entradaForo = entradaForoService.getEntradaForo(idEntrada);
      return new ResponseEntity<>(entradaForo, HttpStatus.OK);
    } catch (EntradaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntradaForo> getEntradaForoById(@PathVariable int id) {
      Optional<EntradaForo> entradaForo = entradaForoService.getEntradaForoById(id);
      if (entradaForo.isPresent()) {
          EntradaForo entradaForoFound = entradaForo.get();
            // Aquí puedes verificar si el Usuario asociado al mensaje no es null antes de
            // devolverlo
          if (entradaForoFound.getAutor() != null) {
            return ResponseEntity.ok(entradaForoFound);
          } else {
                // Manejo del caso donde usuario es null, por ejemplo, devolver un código de
                // error apropiado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
          }
      } else {
        return ResponseEntity.notFound().build();
      }
  }

  // @PutMapping("/{idEntrada}")
  // public ResponseEntity<EntradaForo> updateEntradaForo(@PathVariable Integer idEntrada, @RequestBody EntradaForo entradaForoDetails) {
  //   try {
  //     EntradaForo updatedEntrada = entradaForoService.updateEntradaForo(idEntrada, entradaForoDetails);
  //     return new ResponseEntity<>(updatedEntrada, HttpStatus.OK);
  //   } catch (EntradaForoNotFoundException exception) {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   }
  // }
  // // @PutMapping("/update/{id}")
  // // public ResponseEntity<EntradaForo> updateEntrada(@PathVariable Integer id, @RequestBody EntradaForo entradaForo) {
  // //     EntradaForo updatedEntrada = entradaForoService.updateEntrada(id, entradaForo);
  // //     return ResponseEntity.ok(updatedEntrada);
  // // }
  // // @PutMapping("/{idEntrada}")
  // // public ResponseEntity<EntradaForo>
  // // updateEntradaForo(@PathVariable("idEntrada") Integer idEntrada,
  // // @RequestBody EntradaForo entradaForo) {
  // // try {
  // // EntradaForo updatedEntradaForo =
  // // entradaForoService.updateEntradaForo(idEntrada, entradaForo);
  // // return new ResponseEntity<>(updatedEntradaForo, HttpStatus.OK);
  // // } catch (EntradaForoNotFoundException exception) {
  // // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // // }
  // // }

  @PatchMapping("/{idEntrada}")
  public ResponseEntity<EntradaForo> updateEntradaForo(@PathVariable Integer idEntrada, @RequestBody EntradaForo entradaForoDetails) {
      try {
          EntradaForo updatedEntradaForo = entradaForoService.updateEntradaForo(idEntrada, entradaForoDetails);
          return new ResponseEntity<>(updatedEntradaForo, HttpStatus.OK);
      } catch (EntradaForoNotFoundException exception) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

  @DeleteMapping("/{idEntrada}")
  public ResponseEntity<HttpStatus> deleteEntradaForo(@PathVariable("idEntrada") Integer idEntrada) {
    try {
      entradaForoService.deleteEntradaForo(idEntrada);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (EntradaForoNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
