package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.exceptions.WrongParamsException;

public interface AvgSalaryService {

    double getAvgSalary(int year) throws WrongParamsException;
}
