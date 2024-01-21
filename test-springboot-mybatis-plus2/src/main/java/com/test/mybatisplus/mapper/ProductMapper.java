package com.test.mybatisplus.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mybatisplus.entity.Product;

@DS("slave_1")
public interface ProductMapper extends BaseMapper<Product> {

}