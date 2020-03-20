package com.test.springboot.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 开启认证
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // 定义请求规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启登录功能，效果：如果没有登录，没有权限就会来到登录页面
        http.formLogin()
                .loginPage("/userlogin")
                .usernameParameter("user").passwordParameter("pwd");

        // 1、/login来到登录页
        // 2、重定向到/login?error表示登录失败
        // 3、更多详情规定
        // 4、默认post形式的login代表登录
        // 5、一但定制loginPage，loginPage的post请求就是登录

        // 开启自动配置的注销功能
        // logoutSuccessUrl成功注销返回界面
        http.logout().logoutSuccessUrl("/");
        // 1、访问 /logout 表示用户注销，清空session
        // 2、注销成功会返回 /login?logout

        // 开启记住我功能
        http.rememberMe()
                .rememberMeParameter("remember");
        // 1、登录成功以后，将cookie发送给浏览器保存，以后访问自动带上cookie，检查通过免登录
        // 2、点击注销后会删除cookie

    }

    // 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 认证信息存储到内存中
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("zhangsan").password(passwordEncoder.encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(passwordEncoder.encode("123456")).roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password(passwordEncoder.encode("123456")).roles("VIP1", "VIP3");
    }
}
