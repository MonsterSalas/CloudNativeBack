package com.cloudNativeBack.cloudNativeBack.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;  

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "signos_vitales")
public class SignosVitales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_signo")
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @JsonIgnore  // Evitar la recursión infinita
    private Paciente paciente;

    @NotNull(message = "La fecha de registro es obligatoria")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @Min(value = 0, message = "La saturación de oxígeno no puede ser negativa")
    @Max(value = 100, message = "La saturación de oxígeno no puede superar el 100%")
    @Column(name = "saturacion_oxigeno", precision = 3)
    private Integer saturacionOxigeno;
    
    @DecimalMin(value = "0.0", message = "La temperatura no puede ser negativa")
    @Digits(integer = 5, fraction = 2, message = "La temperatura debe tener un formato válido")
    @Column(name = "temperatura", precision = 5, scale = 2)
    private BigDecimal temperatura;

    @Min(value = 0, message = "La frecuencia cardíaca no puede ser negativa")
    @Digits(integer = 3, fraction = 0, message = "La frecuencia cardíaca debe tener un formato válido")
    @Column(name = "frecuencia_cardiaca", precision = 3)
    private Integer frecuenciaCardiaca;

    @Pattern(regexp = "\\d{2,3}/\\d{2,3}", message = "La presión arterial debe estar en formato '120/80'")
    @Column(name = "presion_arterial", length = 20)
    private String presionArterial;



}
