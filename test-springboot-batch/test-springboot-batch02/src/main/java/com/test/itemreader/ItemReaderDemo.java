package com.test.itemreader;

import com.test.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//@Configuration
public class ItemReaderDemo {

    // 注入创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 注入创建步骤的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job itemReaderDemoJob() {
        return jobBuilderFactory.get("itemReaderDemoJob")
                .start(itemReaderDemoStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step itemReaderDemoStep() {
        return stepBuilderFactory.get("itemReaderDemoStep")
                .chunk(2)
                .reader(itemReaderDemoRead())
                .writer(list->{
                    for (Object item:list){
                        System.out.println(item+"...");
                    }
                }).build();
    }

    /**
     * itemReaderDemoRead 自定义itemReader
     * @return
     */
    @Bean
    public MyReader itemReaderDemoRead() {
        List<String> data = Arrays.asList("鼠","牛","虎","兔");
        return new MyReader(data);
    }

    private static class MyReader implements ItemReader<String> {

        private Iterator<String> iterator;

        //构造函数
        public MyReader(List<String> list) {
            this.iterator = list.iterator();
        }

        @Override
        public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
            //默认一个一个读数据
            if (iterator.hasNext()) {
                return this.iterator.next();
            } else {
                return null;
            }
        }
    }
}



