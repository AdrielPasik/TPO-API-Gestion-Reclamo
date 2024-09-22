package com.api.spring_tpo.controller;

import com.api.spring_tpo.entity.Persona;
import com.api.spring_tpo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    // Obtener todas las personas
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Obtener una persona por documento
    @GetMapping("/{documento}")
    public ResponseEntity<Persona> getPersonaByDocumento(@PathVariable String documento) {
        return personaRepository.findById(documento)
                .map(persona -> ResponseEntity.ok().body(persona))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    // Actualizar una persona existente
    @PutMapping("/{documento}")
    public ResponseEntity<Persona> updatePersona(@PathVariable String documento, @RequestBody Persona personaDetails) {
        return personaRepository.findById(documento)
                .map(persona -> {
                    persona.setNombre(personaDetails.getNombre());
                    Persona updatedPersona = personaRepository.save(persona);
                    return ResponseEntity.ok().body(updatedPersona);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una persona
    @DeleteMapping("/{documento}")
    public ResponseEntity<?> deletePersona(@PathVariable String documento) {
        return personaRepository.findById(documento)
                .map(persona -> {
                    personaRepository.delete(persona);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
