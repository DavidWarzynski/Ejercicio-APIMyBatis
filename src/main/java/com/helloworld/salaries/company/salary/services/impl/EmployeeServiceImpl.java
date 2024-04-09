package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mappers.EmployeeMapper;
import com.helloworld.salaries.company.salary.models.Employee;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Double> getEmployeeSalaryByYear(String employeeCode, int year) {
        return employeeMapper.findByEmployeeCodeAndYear(employeeCode, year);
    }

    public void createEmployeeSalary(String employeeCode, int year) {
        List<Double> salaries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            salaries.add(0.0);
        }
        for (int month = 1; month <= 12; month++) {
            double salary = salaries.get(month - 1);
            employeeMapper.updateMonthlySalary(employeeCode, year, month, salary);
        }
    }

    public List<Employee> searchEmployees(String name, String employeeCode, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return employeeMapper.findAllByNameAndEmployeeCode(name, employeeCode, offset, pageSize);
    }

    public void updateEmployeeMonthlySalary(String employeeCode, int year, int month, double salary) {
        employeeMapper.updateMonthlySalary(employeeCode, year, month, salary);
    }
}
