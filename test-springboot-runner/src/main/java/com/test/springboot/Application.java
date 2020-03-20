package com.test.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 普通
        SpringApplication.run(Application.class, args);

        // 定制
//        Banner banner = new Banner(){
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                System.out.println(environment);
//                System.out.println(sourceClass);
//                out.print("\n=== <<< This is new banner >>> ===\n\n");
//            }
//        };
//
//        new SpringApplicationBuilder(Application.class)
//
//                // 设置当前应用类型
//                .web(WebApplicationType.SERVLET)
//
//                // 设置 banner 横幅打印方式、有关闭、日志、控制台
//                .bannerMode(Banner.Mode.CONSOLE)
//
//                // 设置自定义的 banner
//                .banner(banner)
//
//                // 追加自定义的 initializer 到集合中
//                .initializers()
//
//                // 追加自定义的 listeners 到集合中
//                .listeners()
//
//                .run(args);
    }

}
