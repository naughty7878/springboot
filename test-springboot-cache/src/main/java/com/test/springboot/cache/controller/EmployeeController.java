package com.test.springboot.cache.controller;

import com.test.springboot.cache.bean.Employee;
import com.test.springboot.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {

        return employeeService.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee emp) {

        return employeeService.updateEmp(emp);
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }


    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmployee(@PathVariable("lastName") String lastName) {

        return employeeService.getEmpBylastName(lastName);
    }
}
