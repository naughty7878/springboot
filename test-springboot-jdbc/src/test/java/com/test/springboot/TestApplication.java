package com.test.springboot;

import com.mysql.jdbc.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void context() throws SQLException {
        System.out.println("========");
        System.out.println("dataSource ====" + dataSource);

        Connection connection = dataSource.getConnection();
        System.out.println("connecttion ====" + connection);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        System.out.println(maps);
    }

}
