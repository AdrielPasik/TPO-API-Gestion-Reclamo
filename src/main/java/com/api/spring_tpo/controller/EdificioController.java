package com.api.spring_tpo.controller;

import com.api.spring_tpo.entity.Edificio;
import com.api.spring_tpo.entity.Persona;
import com.api.spring_tpo.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/edificios")
public class EdificioController {

    @Autowired
    private EdificioRepository edificioRepository;

    @GetMapping
    public List<Edificio> getAllEdificios() {
        return edificioRepository.findAll();
    }

    // Obtener edificio por codigo
    @GetMapping("/{codigo}")
    public ResponseEntity<Edificio> getEdificioByCodigo(@PathVariable Long codigo) {
        return edificioRepository.findById(codigo)
                .map(edificio -> ResponseEntity.ok().body(edificio))
                .orElse(ResponseEntity.notFound().build());
    }

    // Agregar un nuevo edificio, dos edificio no pueden tener la misma direccion y nombre
    @PostMapping
    public ResponseEntity<?> createEdificio(@RequestBody Edificio edificio) {
        // Verificar si ya existe un edificio con la misma dirección
        Edificio existingEdificio = edificioRepository.findByNombreAndDireccion(edificio.getNombre(), edificio.getDireccion());
        if (existingEdificio != null) {
            // Retornar un estado de conflicto si se encuentra un edificio con la misma dirección
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!!! El edificio ya existe");
        }
        // Guardar el nuevo edificio
        Edificio nuevoEdificio = edificioRepository.save(edificio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEdificio);
    }


    // modificar edificio
    @PutMapping("/{codigo}")
    public ResponseEntity<Edificio> updateEdificio(@PathVariable Long codigo, @RequestBody Edificio updatedEdificio) {

        Optional<Edificio> existingEdificio = edificioRepository.findById(codigo);

        if (existingEdificio.isPresent()) {
            Edificio edificio = existingEdificio.get();
            // Actualizamos los campos necesarios
            edificio.setNombre(updatedEdificio.getNombre());
            edificio.setDireccion(updatedEdificio.getDireccion());

            // Guardamos los cambios
            Edificio savedEdificio = edificioRepository.save(edificio);
            return ResponseEntity.ok(savedEdificio);
        } else {
            return ResponseEntity.notFound().build();  // Si no se encuentra el edificio
        }
    }

    //eliminar edificio, validar que no tenga unidades
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteEdificio(@PathVariable Long codigo) {
        // Verificar si la unidad existe antes de intentar eliminarla
        if (edificioRepository.existsById(codigo)) {
            edificioRepository.deleteById(codigo); // Eliminar la unidad por identificador
            return ResponseEntity.noContent().build(); // Retorna un código de estado 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna un código de estado 404 Not Found si no se encuentra la unidad
        }
    }
}