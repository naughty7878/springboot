package com.test.config;

import com.test.listener.MyChunkListener;
import com.test.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

//@Configuration
public class ListenerDemo {
    // 注入创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 注入创建步骤的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job listenerJob() {
        return jobBuilderFactory.get("listenerJob")
                .start(step1())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2)// 没读完2次数据，进行一次输出处理
                .faultTolerant() // 容错
                .listener(new MyChunkListener())
                .reader(read())// 读取数据
                .writer(write())
                .build();
    }

    // 写
    @Bean
    public ItemWriter<String> write() {
        return new ItemWriter<String>() {

            @Override
            public void write(List<? extends String> items) throws Exception {
                for(String item: items) {
                    System.out.println(Thread.currentThread().getName() + "------" + item);
                }
            }
        };
    }

    // 读
    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("java", "spring", "mybatis"));
    }
}
