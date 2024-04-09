package com.helloworld.salaries.controllers;

import com.helloworld.salaries.company.salary.services.AvgSalaryService;
import com.helloworld.salaries.exceptions.WrongParamsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Double.class),
                        examples = @ExampleObject(value = "1518.35"))),
        @ApiResponse(responseCode = "400",
                description = "Parámetros incorrectos",
                content = @Content(mediaType = "text/plain",
                schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404",
                description = "No se encontraron datos para el año especificado",
                content = @Content(mediaType = "text/plain",
                        schema = @Schema(implementation = String.class)))
})
public class CompanyController {

    private final AvgSalaryService avgSalaryService;

    public CompanyController(AvgSalaryService avgSalaryService) {
        this.avgSalaryService = avgSalaryService;
    }
    @GetMapping("/salary/{year}/avg")
    @Operation(summary = "Obtener el salario medio de un año" ,
            description = "Devuelve el salario medio de un año")
    @Parameter(name = "year",
            description = "Año para el cual se desea obtener el salario medio",
            in = ParameterIn.PATH,
            required = true,
            example = "2024")
    public ResponseEntity<?> getAvgSalary(@PathVariable int year) {
        Double avgSalary = null;
        try {
            avgSalary = this.avgSalaryService.getAvgSalary(year);
            if (avgSalary == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron datos para el año especificado");
            }
            return ResponseEntity.ok(avgSalary);
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
