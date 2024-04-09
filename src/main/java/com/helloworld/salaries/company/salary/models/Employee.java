package com.helloworld.salaries.company.salary.models;

import java.util.Date;
public class Employee {
    private String employeeCode;
    private String name;
    private Date startDate;
    private Date endDate;
    private Integer officeId;


    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getOfficeId() {
        return officeId;
    }
}