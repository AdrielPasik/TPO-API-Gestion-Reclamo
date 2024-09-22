package com.api.spring_tpo.repository;

import com.api.spring_tpo.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
    Edificio findByNombreAndDireccion(String nombre, String direccion);
}
