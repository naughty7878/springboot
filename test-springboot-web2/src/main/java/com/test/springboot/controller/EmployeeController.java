package com.test.springboot.controller;

import com.test.springboot.dao.DepartmentDao;
import com.test.springboot.dao.EmployeeDao;
import com.test.springboot.entities.Department;
import com.test.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    // 查询所有员工列表
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    // 添加员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 查询所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id , Model model){

        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("员工信息：" + employee);
        // 返回员工列表界面
        // redirect：表示重定向到某个界面
        // forward：表示转发到某个界面
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
