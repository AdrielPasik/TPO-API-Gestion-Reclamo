package com.api.spring_tpo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "duenios")
public class Duenio {

    // Columna "id" es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Relación Many-to-One con la entidad Persona (propietario)
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento", nullable = false)
    private Persona persona;

    // Relación Many-to-One con Unidad (la unidad que posee el dueño)
    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", nullable = false)
    private Unidad unidad;

    // Constructor por defecto
    public Duenio() {
    }

    // Constructor con parámetros
    public Duenio(Persona persona, Unidad unidad) {
        this.persona = persona;
        this.unidad = unidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    // Sobrescribir el método toString para facilitar la depuración
    @Override
    public String toString() {
        return "Duenio{" +
                "id=" + id +
                ", persona=" + persona +
                ", unidad=" + unidad +
                '}';
    }
}
