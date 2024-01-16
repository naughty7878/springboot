package com.test.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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

    @Test
    public void test06() {
        // SELECT id,last_name,age FROM employee WHERE is_deleted=0
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "last_name", "age");
        List<Map<String, Object>> maps = employeeMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee
        // WHERE is_deleted=0 AND (id IN (SElECT id FROM employee WHERE id < 3))
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        String sql = "SElECT id FROM employee WHERE id < 3";
        queryWrapper.inSql("id", sql);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

    @Test
    public void test08() {
        // UPDATE employee SET last_name=? WHERE is_deleted=0 AND (last_name LIKE ?)
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("last_name", "小");
        updateWrapper.set("last_name", "小黑");
        int update = employeeMapper.update(null, updateWrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void test09() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee
        // WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String lastName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (lastName != null && lastName.trim().length() > 0) {
            queryWrapper.like("last_name", lastName);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

    @Test
    public void test10() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee
        // WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String lastName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(lastName != null && lastName.trim().length() > 0, "last_name", lastName);
        queryWrapper.ge(ageBegin != null, "age", ageBegin);
        queryWrapper.le(ageEnd != null, "age", ageEnd);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

    @Test
    public void test11() {
        // SELECT id,last_name,gender,email,age,is_deleted FROM employee
        // WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String lastName = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(lastName != null && lastName.trim().length() > 0, Employee::getLastName, lastName);
        queryWrapper.ge(ageBegin != null, Employee::getAge, ageBegin);
        queryWrapper.le(ageEnd != null, Employee::getAge, ageEnd);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }


    @Test
    public void test12() {
        // UPDATE employee SET last_name=? WHERE is_deleted=0 AND (last_name LIKE ?)
        LambdaUpdateWrapper<Employee> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(Employee::getLastName, "小");
        updateWrapper.set(Employee::getLastName, "小红");
        int update = employeeMapper.update(null, updateWrapper);
        System.out.println("update = " + update);
    }
}
