package com.api.spring_tpo.repository;

import com.api.spring_tpo.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer> {
}