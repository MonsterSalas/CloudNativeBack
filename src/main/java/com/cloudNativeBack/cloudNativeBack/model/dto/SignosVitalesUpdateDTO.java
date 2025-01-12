package com.cloudNativeBack.cloudNativeBack.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignosVitalesUpdateDTO {    
    private LocalDate fechaRegistro;
    
    @Min(value = 0, message = "La saturación de oxígeno no puede ser negativa")
    @Max(value = 100, message = "La saturación de oxígeno no puede superar el 100%")
    private Integer saturacionOxigeno;
    
    @DecimalMin(value = "0.0", message = "La temperatura no puede ser negativa")
    @Digits(integer = 5, fraction = 2, message = "La temperatura debe tener un formato válido")
    private BigDecimal temperatura;
    
    @Min(value = 0, message = "La frecuencia cardíaca no puede ser negativa")
    @Digits(integer = 3, fraction = 0, message = "La frecuencia cardíaca debe tener un formato válido")
    private Integer frecuenciaCardiaca;
    
    @Pattern(regexp = "\\d{2,3}/\\d{2,3}", message = "La presión arterial debe estar en formato '120/80'")
    private String presionArterial;
}