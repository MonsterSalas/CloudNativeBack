package com.cloudNativeBack.cloudNativeBack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudNativeBack.cloudNativeBack.model.SignosVitales;

public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long> {
    SignosVitales findByPacienteId(Long pacienteId);
  
}