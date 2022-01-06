package com.test.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;

//@Configuration
public class NestedDemo {
    // 注入创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 注入创建步骤的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    // Job启动对象
    @Autowired
    private JobLauncher jobLauncher;
//    // Job存储对象
//    @Autowired
//    private JobRepository jobRepository;

    @Resource
    private Job childJobOne;

    @Resource
    private Job childJobTwo;

    @Bean
    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return jobBuilderFactory.get("parentJob")
                .start(childJob1(jobRepository, transactionManager))
                .next(childJob2(jobRepository, transactionManager))
                .build();
    }

    // 返回的是Job类型的Step，特殊的Step
    private Step childJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(childJobTwo)
                .launcher(jobLauncher)// 使用父Job的启动对象
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }

    private Step childJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJobOne)
                .launcher(jobLauncher)// 使用父Job的启动对象
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }
}
