package com.helloworld.salaries.controllers;

import com.helloworld.salaries.company.salary.models.Employee;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.WrongParamsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "OK"),
        @ApiResponse(responseCode = "400",
                description = "Petición inválida",
                content = @Content(mediaType = "text/plain",
                        schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404",
                description = "Recurso no encontrado",
                content = @Content(mediaType = "text/plain",
                        schema = @Schema(implementation = String.class)))
})
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeCode}/salary/{year}")
    @Operation(summary = "Recupera la lista de salarios mensuales del empleado para el año especificado",
            description = "Recupera una lista (12 valores) con el salario mensual del empleado indicado para el año especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Operación exitosa, se encontraron salarios mensuales",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Double.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso: Empleado no encontrado",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getEmployeeSalaryByYear(
            @PathVariable @Parameter(description = "Código del empleado del que se desea el salario") String employeeCode,
            @PathVariable @Parameter(description = "Año para el cual se desea obtener el salario") int year) {
        try {
            List<Double> monthlySalaries = employeeService.getEmployeeSalaryByYear(employeeCode, year);
            if (monthlySalaries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron datos para el año especificado");
            }
            return ResponseEntity.ok(monthlySalaries);
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{employeeCode}/salary/{year}")
    @Operation(summary = "Crea los salarios mensuales para el empleado y año especificados",
            description = "Crea, si no existe, la lista de salarios mensuales para el empleado indicado y el año especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Operación exitosa, se encontraron empleados",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "400", description = "Petición inválida: Parámetros inválidos o incorrectos",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> createEmployeeSalary(
            @PathVariable @Parameter(description = "Código del empleado del que se desea la lista de salario") String employeeCode,
            @PathVariable @Parameter(description = "Año para el cual se desea obtener la lista de salario") int year,
            @RequestBody @Parameter(description = "Salario que se desea asignar al empleado") double salary) {
        try {
            employeeService.createEmployeeSalary(employeeCode, year, salary);
            return ResponseEntity.ok().build();
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Busca empleados con filtros opcionales y paginación",
            description = "Realiza una búsqueda paginada de empleados según el nombre y código de empleado proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Operación exitosa, se encontraron empleados",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "400", description = "Petición inválida: Parámetros inválidos o incorrectos",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> searchEmployees(
            @RequestParam(required = true) @Parameter(description = "Nombre del empleado para filtrar la búsqueda") String name,
            @RequestParam(required = true) @Parameter(description = "Código del empleado para filtrar la búsqueda") String employeeCode,
            @RequestParam(defaultValue = "1") @Parameter(description = "Número de página para la paginación") int page) {
        try {
            List<Employee> employees = employeeService.searchEmployees(name, employeeCode, page, 10);
            if (employees.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employees);
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{employeeCode}/salary/{year}/month/{month}")
    @Operation(summary = "Actualiza el salario mensual de un empleado para un mes específico",
            description = "Actualiza el salario mensual de un empleado para un mes específico del año especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Operación exitosa, se actualizó el salario mensual"),
            @ApiResponse(responseCode = "400", description = "Petición inválida: Parámetros inválidos o incorrectos",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> updateEmployeeMonthlySalary(
            @PathVariable @Parameter(description = "Código del empleado") String employeeCode,
            @PathVariable @Parameter(description = "Año para el cual se actualizará el salario") int year,
            @PathVariable @Parameter(description = "Mes para el cual se actualizará el salario") int month,
            @RequestParam @Parameter(description = "Nuevo salario") double salary) {
        try {
            employeeService.updateEmployeeMonthlySalary(employeeCode, year, month, salary);
            return ResponseEntity.ok().build();
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}