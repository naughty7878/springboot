package com.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReddision {

    @Autowired
    ApplicationContext context;


    @Autowired
    RedissonClient redissonClient;


    @Test
    public void testRedisson() {
        String lockKey = "abc";
        RLock lock = redissonClient.getLock(lockKey);
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：运行");
                try {
                    boolean b = lock.tryLock(10, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + ":获得锁" + b);
                    Thread.sleep(10000);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "已解锁");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：运行");
                try {
                    boolean b = lock.tryLock(10, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + ":获得锁" + b);
                    Thread.sleep(10000);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "已解锁");
            }
        }.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
