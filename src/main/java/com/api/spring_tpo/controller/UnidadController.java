package com.api.spring_tpo.controller;

import com.api.spring_tpo.entity.Unidad;
import com.api.spring_tpo.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

    @Autowired
    private UnidadRepository unidadRepository;

    // Obtener todas las unidades
    @GetMapping
    public List<Unidad> getAllUnidades() {
        return unidadRepository.findAll();
    }

    // Obtener una unidad por identificador
    @GetMapping("/{identificador}")
    public ResponseEntity<Unidad> getUnidadById(@PathVariable int identificador) {
        return unidadRepository.findById(identificador)
                .map(unidad -> ResponseEntity.ok().body(unidad))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva unidad
    @PostMapping
    public Unidad createUnidad(@RequestBody Unidad unidad) {
        return unidadRepository.save(unidad);
    }

    // Actualizar una unidad existente
    @PutMapping("/{identificador}")
    public ResponseEntity<Unidad> updateUnidad(@PathVariable int identificador, @RequestBody Unidad unidadDetails) {
        return unidadRepository.findById(identificador)
                .map(unidad -> {
                    unidad.setPiso(unidadDetails.getPiso());
                    unidad.setNumero(unidadDetails.getNumero());
                    unidad.setHabitado(unidadDetails.getHabitado());
                    unidad.setEdificio(unidadDetails.getEdificio());
                    Unidad updatedUnidad = unidadRepository.save(unidad);
                    return ResponseEntity.ok().body(updatedUnidad);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una unidad
    @DeleteMapping("/{identificador}")
    public ResponseEntity<String> deleteUnidad(@PathVariable int identificador) {
        if (unidadRepository.existsById(identificador)) {
            unidadRepository.deleteById(identificador);
            return ResponseEntity.ok("Unidad eliminada exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una unidad con el identificador: " + identificador);
        }
    }
}
