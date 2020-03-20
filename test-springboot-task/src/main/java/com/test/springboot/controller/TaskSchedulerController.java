package com.test.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TaskSchedulerController {

    // 注入TaskScheduler
    @Autowired
    TaskScheduler taskScheduler;

    @RequestMapping("/taskScheduled")
    public String executor(){

        taskScheduler.schedule(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +  ":taskScheduled ..... start " + new Date());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +  ":taskScheduled ..... end " + new Date());
            }
        }, new Date(new Date().getTime() + 5000));
        return "success";
    }
}
