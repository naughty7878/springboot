package com.test.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void test01() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee
        // WHERE is_deleted=0 AND (last_name LIKE ? AND age BETWEEN ? AND ? OR email IS NULL)
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name", "a")
                .between("age", 20, 30)
                .or()
                .isNull("email");
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

    @Test
    public void test03() {
        // UPDATE employee SET is_deleted=1 WHERE is_deleted=0 AND (email LIKE ?)
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("email", "lilei");
        int delete = employeeMapper.delete(queryWrapper);
        System.out.println("delete = " + delete);
    }

    @Test
    public void test04() {
        // UPDATE employee SET last_name=?, email=?
        // WHERE is_deleted=0 AND (last_name LIKE ? AND age BETWEEN ? AND ? OR email IS NULL)
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name", "a")
                .between("age", 20, 30)
                .or()
                .isNull("email");
        Employee employee = new Employee();
        employee.setLastName("小明");
        employee.setEmail("test@xx.com");
        int update = employeeMapper.update(employee, queryWrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void test05() {
        // UPDATE employee SET last_name=?, email=?
        // WHERE is_deleted=0 AND (last_name LIKE ? AND (age >= ? OR email IS NULL))
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name", "a")
                .and(i -> i.ge("age", 20).or().isNull("email"));
        Employee employee = new Employee();
        employee.setLastName("小明");
        employee.setEmail("test@xx.com");
        int update = employeeMapper.update(employee, queryWrapper);
        System.out.println("update = " + update);
    }
}
