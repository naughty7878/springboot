package com.test.springboot.config;

import com.test.springboot.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @EnableWebMvc // 全面接管SpringMVC，所有的WebMvc自动配置都失效，如静态资源的访问都失效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // 添加视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        // 浏览器访问 "/success2" 重定向到 "/success"
//        registry.addRedirectViewController("/success2", "/success");
//        // 浏览器访问 "/success2" 转发 "/success"
//        registry.addViewController("/success3").setViewName("/success");

        // 首页
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");

        registry.addViewController("/main.html").setViewName("main");

    }

    // 添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        // springboot静态映射已做好，无需在拦截器中处理静态资源
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/", "/index.html", "/user/login");
//    }
}
