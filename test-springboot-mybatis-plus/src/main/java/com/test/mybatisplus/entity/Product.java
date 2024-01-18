package com.test.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
@TableName("t_product")
public class Product {

    @TableId
    private Long id;

    private String name;

    private Integer price;
    // 乐观锁版本号字段
    @Version
    private Integer version;
}
