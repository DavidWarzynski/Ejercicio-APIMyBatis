package com.helloworld.salaries.company.salary.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AvgSalaryMapper {
    Double getAvgSalary(int year);
}

