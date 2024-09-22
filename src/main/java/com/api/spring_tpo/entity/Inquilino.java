package com.api.spring_tpo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inquilinos") // Nombre de la tabla en la base de datos
public class Inquilino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el ID
    private int id;

    @Column(nullable = false)
    private int identificador;

    @Column(nullable = false, length = 50) // Definimos que sea varchar en la base de datos
    private String documento;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
