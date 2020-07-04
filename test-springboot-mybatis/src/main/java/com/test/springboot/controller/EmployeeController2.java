package com.test.springboot.controller;

import com.test.springboot.entities.Employee;
import com.test.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController2 {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取员工列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/employees", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    public String getAllEmployeesHtml(Model model)
    {
        model.addAttribute("employees", employeeMapper.getEmps());
        return "employees";
    }

    @RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    @ResponseBody
    public List<Employee>  getAllEmployeesJSON()
    {
        return employeeMapper.getEmps();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee get(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody Employee employee){
        System.out.println("post ====" + employee);
        return "success";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Employee employee){
        System.out.println("put ====" + employee);
        return "success";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete ====" + id);
        return "success";
    }

}
