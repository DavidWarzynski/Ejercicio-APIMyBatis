package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mappers.EmployeeMapper;
import com.helloworld.salaries.company.salary.models.Employee;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.WrongParamsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Double> getEmployeeSalaryByYear(String employeeCode, int year) throws WrongParamsException {
        validateEmployeeCode(employeeCode);
        validateYear(year);
        List<Double> monthlySalaries = employeeMapper.findByEmployeeCodeAndYear(employeeCode, year);
        return monthlySalaries;
    }

    public void createEmployeeSalary(String employeeCode, int year) throws WrongParamsException {
        validateEmployeeCode(employeeCode);
        validateYear(year);
        List<Double> salaries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            salaries.add(0.0);
        }
        for (int month = 1; month <= 12; month++) {
            double salary = salaries.get(month - 1);
            employeeMapper.updateMonthlySalary(employeeCode, year, month, salary);
        }
    }

    public List<Employee> searchEmployees(String name, String employeeCode, int page, int pageSize) throws WrongParamsException {
        validateName(name);
        validateEmployeeCode(employeeCode);
        validatePage(page);
        validatePageSize(pageSize);
        int offset = (page - 1) * pageSize;
        List<Employee> employees = employeeMapper.findAllByNameAndEmployeeCode(name, employeeCode, offset, pageSize);
        return employees;
    }

    public void updateEmployeeMonthlySalary(String employeeCode, int year, int month, double salary) throws WrongParamsException {
        validateEmployeeCode(employeeCode);
        validateYear(year);
        validateMonth(month);
        validateSalary(salary);
        employeeMapper.updateMonthlySalary(employeeCode, year, month, salary);
    }

    private void validateEmployeeCode(String employeeCode) throws WrongParamsException {
        if (employeeCode == null || employeeCode.isEmpty()) {
            throw new WrongParamsException("employeeCode");
        }
    }

    private void validateYear(int year) throws WrongParamsException {
        if (year < 2000 || year > 3000) {
            throw new WrongParamsException("year");
        }
    }

    private void validateName(String name) throws WrongParamsException {
        if (name == null || name.isEmpty()) {
            throw new WrongParamsException("name");
        }
    }

    private void validatePage(int page) throws WrongParamsException {
        if (page <= 0) {
            throw new WrongParamsException("page");
        }
    }

    private void validatePageSize(int pageSize) throws WrongParamsException {
        if (pageSize <= 0) {
            throw new WrongParamsException("pageSize");
        }
    }

    private void validateMonth(int month) throws WrongParamsException {
        if (month < 1 || month > 12) {
            throw new WrongParamsException("month");
        }
    }

    private void validateSalary(double salary) throws WrongParamsException {
        if (salary <= 0) {
            throw new WrongParamsException("salary");
        }
    }
}
