package com.cloudNativeBack.cloudNativeBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudNativeBack.cloudNativeBack.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
