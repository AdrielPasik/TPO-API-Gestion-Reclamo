package com.api.spring_tpo.repository;

import com.api.spring_tpo.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {
    // Puedes agregar métodos personalizados si es necesario
}
