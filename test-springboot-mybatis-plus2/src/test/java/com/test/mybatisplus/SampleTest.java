package com.test.mybatisplus;

import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.entity.Product;
import com.test.mybatisplus.mapper.EmployeeMapper;
import com.test.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
public class SampleTest {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ProductMapper productMapper;


    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> userList = employeeMapper.selectList(null);
        userList.forEach(System.out::println);
        List<Product> productList = productMapper.selectList(null);
        productList.forEach(System.out::println);
    }

}