package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.company.salary.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Double> getEmployeeSalaryByYear(String employeeCode, int year);

    void createEmployeeSalary(String employeeCode, int year);

    List<Employee> searchEmployees(String name, String employeeCode, int page, int pageSize);

    void updateEmployeeMonthlySalary(String employeeCode, int year, int month, double salary);
}
