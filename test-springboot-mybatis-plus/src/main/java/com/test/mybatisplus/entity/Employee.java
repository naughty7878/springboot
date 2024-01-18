package com.test.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.test.mybatisplus.enums.SexEnum;
import lombok.Data;

import java.math.BigDecimal;

// @TableName 指定表名
//@TableName(value = "test_employee")
// 默认是：类名 TestEmployee -> 表名 test_employee
@Data
public class Employee {

    // @TableId 指定主键策略
    @TableId(type = IdType.AUTO)
    private Long id;
//    @TableField(value = "last_name")
    private String lastName;

    private SexEnum gender;
    private String email;
    private Integer age;
    @TableField(exist = false)
    private BigDecimal salary;

    @TableLogic
    private Integer isDeleted;
}
