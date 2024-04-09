package com.helloworld.salaries.company.salary.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Date;
@Schema(description = "Modelo de empleado de la empresa")
public class Employee {
    @Schema(description = "Código de empleado", example = "000123")
    @NotNull
    @NotBlank
    @Size(min = 6, max = 6)
    private String employeeCode;

    @Schema(description = "Nombre del empleado", example = "Juan Pérez Gómez")
    @NotNull
    @NotBlank
    private String name;

    @Schema(description = "Fecha de inicio", example = "2022")
    @NotNull
    @Min(2000)
    @Max(3000)
    private Integer startDate;

    @Schema(description = "Fecha de baja", example = "2023")
    @Min(2000)
    @Max(3000)
    private Integer endDate;

    @Schema(description = "Indentificador de la oficina", example = "1")
    @NotNull
    private Integer officeId;
}