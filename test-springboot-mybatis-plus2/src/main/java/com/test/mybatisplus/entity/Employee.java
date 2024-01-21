package com.test.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Employee {

    // @TableId 指定主键策略
    @TableId(type = IdType.AUTO)
    private Long id;
//    @TableField(value = "last_name")
    private String lastName;

    private String email;
    private Integer age;

}
