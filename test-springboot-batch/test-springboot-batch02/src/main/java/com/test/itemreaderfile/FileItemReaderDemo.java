package com.test.itemreaderfile;

import com.test.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.List;

//@Configuration
public class FileItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job FileItemReaderDemo() {
        return jobBuilderFactory.get("FileItemReaderDemo")
                .start(FileItemReaderDemoStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step FileItemReaderDemoStep() {

        return stepBuilderFactory.get("FileItemReaderDemoStep")
                .<Customer,Customer>chunk(5)
                .reader(fileItemReaderDemoReader())
                .writer(fileFileItemWriter())
                .build();
    }

    @Bean
    public ItemWriter<? super Customer> fileFileItemWriter() {
        return new ItemWriter<Customer>(){
            @Override
            public void write(List<? extends Customer> list) throws Exception {
                for (Customer customer:list){
                    System.out.println(customer);
                }
            }
        };
    }

    @Bean
    public FlatFileItemReader<Customer> fileItemReaderDemoReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setResource(new ClassPathResource("customer.txt"));
        //跳过第一行
        reader.setLinesToSkip(1);
        //数据解析
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","fistName","lastName","birthday"});
        //把解析出的一个数据映射为Customer对象
        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {

            /**
             * @Description: mapFieldSet 映射
             * @Param: [fieldSet]
             * @Return: com.example.springbatchdemo.pojo.Customer
             */
            @Override
            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
                Customer customer = new Customer();
                customer.setId(fieldSet.readLong("id"));
                customer.setFistName(fieldSet.readString("fistName"));
                customer.setLastName(fieldSet.readString("lastName"));
                customer.setBirthday(fieldSet.readString("birthday"));
                return customer;
            }
        });
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
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
