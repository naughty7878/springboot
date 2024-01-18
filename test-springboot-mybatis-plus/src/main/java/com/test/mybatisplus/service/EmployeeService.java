package com.test.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.mybatisplus.entity.Employee;

/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现
 */
public interface EmployeeService extends IService<Employee>{

}