package com.test.springboot.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @EnableWebMvc // 全面接管SpringMVC，所有的WebMvc自动配置都失效，如静态资源的访问都失效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问 "/success2" 重定向到 "/success"
        registry.addRedirectViewController("/success2", "/success");
        // 浏览器访问 "/success2" 转发 "/success"
        registry.addViewController("/success3").setViewName("/success");
    }
}
