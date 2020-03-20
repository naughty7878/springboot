package com.test.springboot.i18n;


import com.test.springboot.i18n.component.MyLocaleResoler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        // Spring应用启动
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResoler();
    }
}
