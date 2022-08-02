package com.test.mybatisplus;

import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.entity.JpaUser;
import com.test.mybatisplus.mapper.EmployeeMapper;
import com.test.mybatisplus.mapper.JpaUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private JpaUserMapper jpaUserMapper;

    @Test
    public void test01() {
        Employee employee = new Employee();
        employee.setEmail("xx@qq.com");
        employee.setLastName("小黑");
        employee.setGender("1");
        employee.setId(4);
        int insert = employeeMapper.insert(employee);
        System.out.println("insert = " + insert);
        System.out.println("employee = " + employee);
    }

    @Test
    public void test02() {
        JpaUser jpaUser = new JpaUser();
        jpaUser.setEmail("xx@qq.com");
        jpaUser.setLastName("小黑");
        jpaUser.setId(4);
        int insert = jpaUserMapper.insert(jpaUser);
        System.out.println("insert = " + insert);
        System.out.println("jpaUser = " + jpaUser);
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> userList = employeeMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}