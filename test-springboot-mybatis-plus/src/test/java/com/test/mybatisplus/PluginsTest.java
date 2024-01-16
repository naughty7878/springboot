package com.test.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.mapper.EmployeeMapper;
import com.test.mybatisplus.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PluginsTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testPage() {
        Page<Employee> page = new Page<>(2, 2);
        employeeMapper.selectPage(page, null);
        System.out.println("page = " + page);
        System.out.println("page.getRecords() = " + page.getRecords());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.hasNext() = " + page.hasNext());
        System.out.println("page.hasPrevious() = " + page.hasPrevious());
    }

    @Test
    public void testPageVo() {
        Page<Employee> page = new Page<>(1, 2);
        employeeMapper.selectPageVo(page, 20);
        System.out.println("page = " + page);
        System.out.println("page.getRecords() = " + page.getRecords());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.hasNext() = " + page.hasNext());
        System.out.println("page.hasPrevious() = " + page.hasPrevious());
    }

}