package com.test.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TaskExecutorController {

    // 注入任务执行器
    @Autowired
    TaskExecutor taskExecutor;


    @RequestMapping("/taskExecutor")
    public String executor(){
        // 执行任务
        taskExecutor.execute(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +  ":taskExecutor ..... start " + new Date());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +  ":taskExecutor ..... end " + new Date());
            }
        });
        return "success";
    }
}
