package com.test.itemreaderxml;

import com.test.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class XmlItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job xmlItemReaderDemoJob() {
        return jobBuilderFactory.get("xmlItemReaderDemoJob")
                .start(xmlItemReaderDemoStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step xmlItemReaderDemoStep() {

        return stepBuilderFactory.get("xmlItemReaderDemoStep")
                .<Customer,Customer>chunk(5)
                .reader(xmlFileReader())
                .writer(xmlFileWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<? extends Customer> xmlFileReader() {
        StaxEventItemReader<Customer> reader = new StaxEventItemReader<Customer>();
        //指定文件位置
        reader.setResource(new ClassPathResource("customer.xml"));
        //跳过第一行
        reader.setFragmentRootElementName("customers");
        //把xml转成对象
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        //告诉unmarshaller把xml转成什么类型
        Map<String,Class> map = new HashMap<>();
        map.put("customer",Customer.class);
        unmarshaller.setAliases(map);

        reader.setUnmarshaller(unmarshaller);
        return reader;
    }

    @Bean
    @StepScope
    public ItemWriter<? super Customer> xmlFileWriter() {
        return new ItemWriter<Customer>() {
            @Override
            public void write(List<? extends Customer> list) throws Exception {
                for (Customer customer:list){
                    System.out.println(customer);
                }
            }
        };
    }

    private class Customer {

        long id;
        String fistName;
        String lastName;
        String birthday;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFistName() {
            return fistName;
        }

        public void setFistName(String fistName) {
            this.fistName = fistName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", fistName='" + fistName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", birthday='" + birthday + '\'' +
                    '}';
        }
    }
}
