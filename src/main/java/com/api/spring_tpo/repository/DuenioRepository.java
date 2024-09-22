package com.api.spring_tpo.repository;

import com.api.spring_tpo.entity.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Integer> {
    // Métodos personalizados si son necesarios
}
