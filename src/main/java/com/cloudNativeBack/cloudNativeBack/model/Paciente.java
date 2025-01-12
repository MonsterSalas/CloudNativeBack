package com.cloudNativeBack.cloudNativeBack.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACIENTE")
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El apellido es obligatorio")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "edad", precision = 3)
    private Integer edad;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @NotNull(message = "El RUT es obligatorio")
    @Column(name = "rut", nullable = false, unique = true, length = 20)
    private String rut;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso = LocalDate.now();

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "estado_paciente")
    private String estadoPaciente;

    @Column(name = "email")
    private String email;
    
    @Column(name = "genero")
    private String genero;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignosVitales> signosVitales;



}
