package com.test.springboot.mapper;

import com.test.springboot.entities.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface EmployeeMapper {

    @Select("select * from employee")
    public List<Employee> getEmps();

    public Employee getEmpById(Integer id);

}
