package com.test.mybatisplus;

import com.test.mybatisplus.entity.Employee;
import com.test.mybatisplus.entity.Product;
import com.test.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void test01(){
        Product product = productMapper.selectById(1l);
        System.out.println("product = " + product);
    }

    @Test
    public void test02(){

        Product productLi = productMapper.selectById(1l);
        System.out.println("productLi = " + productLi);
        System.out.println("productLi.getPrice() = " + productLi.getPrice());
        Product productWan = productMapper.selectById(1l);
        System.out.println("productWan = " + productWan);
        System.out.println("productWan.getPrice() = " + productWan.getPrice());

        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        productWan.setPrice(productWan.getPrice() - 30);
        int i = productMapper.updateById(productWan);
        if (i == 0) {
            productWan = productMapper.selectById(1l);
            productWan.setPrice(productWan.getPrice() - 30);
            productMapper.updateById(productWan);
        }

        Product productBoss = productMapper.selectById(1l);
        System.out.println("productBoss = " + productWan);
        System.out.println("productBoss.getPrice() = " + productBoss.getPrice());

    }
}
