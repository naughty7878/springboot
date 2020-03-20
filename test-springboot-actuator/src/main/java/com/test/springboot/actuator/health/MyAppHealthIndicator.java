package com.test.springboot.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

// 注入Spring容器中
@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // 自定义的检查方法
        // Health build = Health.up().build();
        return Health.down().withDetail("msg", "服务异常").build();
    }
}
