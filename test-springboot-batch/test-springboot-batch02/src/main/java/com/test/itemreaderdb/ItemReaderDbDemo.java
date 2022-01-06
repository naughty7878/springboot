package com.test.itemreaderdb;

import com.test.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Configuration
public class ItemReaderDbDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    private ItemWriter<? super User> dbJdbcWriter = new DbJdbcWriter();

    @Bean
    public Job ItemReaderDbDemoJob(){
        return jobBuilderFactory.get("itemReaderDbDemoJob")
                .start(itemReaderDbStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step itemReaderDbStep() {
        return stepBuilderFactory.get("itemReaderDemoStep")
                .<User, User>chunk(2)
                .reader(dbJdbcReader())
                .writer(dbJdbcWriter)
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> dbJdbcReader() {
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<User>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(2);
        //把读取到的记录转换成User对象
        reader.setRowMapper(new RowMapper<User>() {
            /**
             * @Description: 结果集的映射
             * @Param: [resultSet, i]
             * @Return: com.example.springbootjdbc.pojo.Users
             */
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User users = new User();
                users.setId(resultSet.getInt("id"));
                users.setAge(resultSet.getInt("age"));
                users.setPhone(resultSet.getString("phone"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(resultSet.getString("email"));
                return users;
            }
        });
        //指定sq1语句
        MySqlPagingQueryProvider provider =new MySqlPagingQueryProvider () ;
        provider.setSelectClause ("id,username,age,phone,email") ;
        provider.setFromClause ("from user") ;
        //指定根据哪个字段进行排序
        Map<String, Order> sort = new HashMap<>(1) ;
        sort.put("id", Order.ASCENDING);
        provider.setSortKeys(sort);
        reader.setQueryProvider(provider);
        return reader ;
    }

    private class DbJdbcWriter implements ItemWriter<User> {

        @Override
        public void write(List<? extends User> list) throws Exception {
            for (User user : list) {
                System.out.println(user);
            }
        }
    }

    private class User {
        int id;
        int age;
        String phone;
        String username;
        String email;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", age=" + age +
                    ", phone='" + phone + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
