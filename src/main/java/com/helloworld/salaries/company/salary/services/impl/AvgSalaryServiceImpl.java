package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.company.salary.mappers.AvgSalaryMapper;
import com.helloworld.salaries.exceptions.WrongParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvgSalaryServiceImpl implements AvgSalaryService {

    private AvgSalaryMapper avgSalaryMapper;

    @Autowired
    public AvgSalaryServiceImpl(AvgSalaryMapper avgSalaryMapper) {
        this.avgSalaryMapper = avgSalaryMapper;
    }

    @Override
    public double getAvgSalary(int year) throws WrongParamsException {
        Double avgSalary = avgSalaryMapper.getAvgSalary(year);
        if (avgSalary == null) {
            throw new WrongParamsException("No data found for the given year.");
        }
        return avgSalary;
    }
}
