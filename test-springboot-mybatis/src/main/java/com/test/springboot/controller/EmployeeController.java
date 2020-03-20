package com.test.springboot.controller;

import com.test.springboot.entities.Employee;
import com.test.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/emps")
    public List<Employee> emps(){
        return employeeMapper.getEmps();
    }

    @RequestMapping("/emp/{id}")
    public Employee emp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

}
