package com.test.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Job监听器
 */
public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("jobExecution.getJobInstance().getJobName() = " + jobExecution.getJobInstance().getJobName() + "-----beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("jobExecution.getJobInstance().getJobName() = " + jobExecution.getJobInstance().getJobName() + "-----afterJob");
    }
}
