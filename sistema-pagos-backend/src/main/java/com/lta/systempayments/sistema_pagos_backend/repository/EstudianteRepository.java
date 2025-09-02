package com.lta.systempayments.sistema_pagos_backend.repository;

import com.lta.systempayments.sistema_pagos_backend.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    Estudiante findByCodigo(String codigo);

    List<Estudiante> findByProgramaId(String programaId);
}
