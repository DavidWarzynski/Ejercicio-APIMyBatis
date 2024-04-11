package com.helloworld.salaries.company.salary.mappers;

import com.helloworld.salaries.company.salary.models.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE NOMBREEMPLEADO LIKE CONCAT('%', #{name}, '%') AND CODEMPLEADO LIKE CONCAT('%', #{employeeCode}, '%') LIMIT #{limit} OFFSET #{offset}")
    List<Employee> findAllByNameAndEmployeeCode(@Param("name") String name, @Param("employeeCode") String employeeCode, @Param("offset") int offset, @Param("limit") int limit);

    @Update("UPDATE salary SET SALARY = #{salary} WHERE CODEMPLEADO = #{employeeCode} AND SALARYYEAR = #{year} AND SALARYMONTH = #{month}")
    void updateMonthlySalary(@Param("employeeCode") String employeeCode, @Param("year") int year, @Param("month") int month, @Param("salary") double salary);

    @Insert("INSERT INTO salary (CODEMPLEADO, SALARYYEAR, SALARYMONTH, SALARY) VALUES (#{employeeCode}, #{year}, #{month}, #{salary})")
    void insertMonthlySalary(@Param("employeeCode") String employeeCode, @Param("year") int year, @Param("month") int month, @Param("salary") double salary);

    @Select("SELECT COUNT(*) FROM salary WHERE CODEMPLEADO = #{employeeCode} AND SALARYYEAR = #{year} AND SALARYMONTH = #{month}")
    boolean checkSalaryExists(@Param("employeeCode") String employeeCode, @Param("year") int year, @Param("month") int month);
    @Select("SELECT SALARY FROM salary WHERE CODEMPLEADO = #{employeeCode} AND SALARYYEAR = #{year}")
    List<Double> findByEmployeeCodeAndYear(@Param("employeeCode") String employeeCode, @Param("year") int year);
}
