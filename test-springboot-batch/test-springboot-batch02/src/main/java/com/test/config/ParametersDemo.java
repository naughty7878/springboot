package com.test.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
public class ParametersDemo {

    // 注入创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 注入创建步骤的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parameterJob(){
        return jobBuilderFactory.get("parameterJob")
                .start(parameterStep())
                .build();
    }

    // Job执行的是Step，Job使用的数据肯定是在Step中使用
    // 那我们只需要给Step传递数据
    // 使用监听，使用Step级别的监听来传递数据
    @Bean
    public Step parameterStep() {
        return stepBuilderFactory.get("parameterStep")
                .listener(new ParameterStepListener())
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
                        System.out.println("jobParameters = " + jobParameters);
                        // 输出接收到的参数的值
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}

class ParameterStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Map<String, JobParameter> parameters = stepExecution.getJobParameters().getParameters();
        Map<String, JobParameter> parameters1 = new JobParametersBuilder()
                .addString("para1", "value1")
                .addString("para2", "value2")
                .toJobParameters().getParameters();

        parameters.putAll(parameters1);

        System.out.println("parameters = " + parameters);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}