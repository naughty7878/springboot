package com.test.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(0, "女");

    @EnumValue
    private Integer sex;
    private String sexName;

}
