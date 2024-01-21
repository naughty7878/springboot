package com.test.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.mybatisplus.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 * 基于Mybatis： 在Mapper接口中编写CURD的相关方法 根据Mapper接口所对应的SQL映射文件 以及 方法对应的SQL语句
 * 基于MP：  让XxxMapper接口继承 BaseMapper接口即可
 *          BaseMapper<T> ： 泛型指定的就是当前Mapper接口所操作的实体类型
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    // 自定义分页
    Page<Employee> selectPageVo(@Param("page") Page<Employee> page, @Param("age") Integer age);
}