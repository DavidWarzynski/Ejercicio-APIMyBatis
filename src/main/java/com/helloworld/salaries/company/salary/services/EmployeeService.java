package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.company.salary.models.Employee;
import com.helloworld.salaries.exceptions.WrongParamsException;

import java.util.List;

public interface EmployeeService {

    List<Double> getEmployeeSalaryByYear(String employeeCode, int year)throws WrongParamsException;

    void createEmployeeSalary(String employeeCode, int year, Double salary)throws WrongParamsException;

    List<Employee> searchEmployees(String name, String employeeCode, int page, int pageSize)throws WrongParamsException;

    void updateEmployeeMonthlySalary(String employeeCode, int year, int month, double salary)throws WrongParamsException;
}
