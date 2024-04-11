package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.exceptions.WrongParamsException;

public interface AvgSalaryService {

    Double getAvgSalary(int year) throws WrongParamsException;
}
