package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mappers.AvgSalaryMapper;
import com.helloworld.salaries.company.salary.services.AvgSalaryService;
import com.helloworld.salaries.exceptions.WrongParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AvgSalaryServiceImpl implements AvgSalaryService {

    private AvgSalaryMapper avgSalaryMapper;

    @Autowired
    public AvgSalaryServiceImpl(AvgSalaryMapper avgSalaryMapper) {
        this.avgSalaryMapper = avgSalaryMapper;
    }

    @Override
    public Double getAvgSalary(int year) throws WrongParamsException {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < 2000 || year> currentYear) {
            throw new WrongParamsException("year");
        }
        return avgSalaryMapper.getAvgSalary(year);
    }
}
