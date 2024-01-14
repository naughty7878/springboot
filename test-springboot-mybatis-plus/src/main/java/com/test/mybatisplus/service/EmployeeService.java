package com.test.mybatisplus.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee> {

}