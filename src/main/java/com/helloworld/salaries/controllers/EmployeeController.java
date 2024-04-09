package com.helloworld.salaries.controllers;

import com.helloworld.salaries.company.salary.models.Employee;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeCode}/salary/{year}")
    public ResponseEntity<?> getEmployeeSalaryByYear(@PathVariable String employeeCode, @PathVariable int year) {
        List<Double> monthlySalaries = employeeService.getEmployeeSalaryByYear(employeeCode, year);
        if (monthlySalaries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monthlySalaries);
    }

    @PostMapping("/{employeeCode}/salary/{year}")
    public ResponseEntity<?> createEmployeeSalary(@PathVariable String employeeCode, @PathVariable int year) {
        if (year < 2000 || year > 3000) {
            return ResponseEntity.badRequest().build();
        }
        employeeService.createEmployeeSalary(employeeCode, year);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> searchEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) String employeeCode, @RequestParam(defaultValue = "1") int page) {
        if (page <= 0) {
            return ResponseEntity.badRequest().body("Page number should be greater than zero.");
        }

        int pageSize = 10; // Set your desired page size
        List<Employee> employees = employeeService.searchEmployees(name, employeeCode, page, pageSize);
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{employeeCode}/salary/{year}/month/{month}")
    public ResponseEntity<?> updateEmployeeMonthlySalary(@PathVariable String employeeCode, @PathVariable int year, @PathVariable int month, @RequestParam double salary) {
        if (salary <= 0) {
            return ResponseEntity.badRequest().body("Salary should be greater than zero.");
        }
        employeeService.updateEmployeeMonthlySalary(employeeCode, year, month, salary);
        return ResponseEntity.ok().build();
    }
}
