package com.test.springboot.cache.service;

import com.test.springboot.cache.bean.Employee;
import com.test.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//@CacheConfig(cacheNames = {"emp"})
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames="emp", key = "'emp' + #id" /*keyGenerator = "myKeyGenerator"*/)
    public Employee getEmp(Integer id){
        System.out.println("===查询" + id + "号员工");
        return employeeMapper.getEmpById(id);
    }

    @CachePut(cacheNames = "emp", key = "#p0.id+100")
    public Employee updateEmp(Employee emp) {
        employeeMapper.updateEmp(emp);
        return emp;
    }

//    @CacheEvict(cacheNames = "emp", key = "#id+100" )
    @CacheEvict(cacheNames = "emp", allEntries = true, beforeInvocation = false)
    public void deleteEmp(Integer id) {
        System.out.println("删除员工" + id);
        int n = 1/0;
    }



    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id+100"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpBylastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
