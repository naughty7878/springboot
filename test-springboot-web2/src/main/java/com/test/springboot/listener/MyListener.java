package com.test.springboot.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class MyListener implements ServletContextListener {

    public  void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized.....");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed.....");
    }
}
