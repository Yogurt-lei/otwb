package com.yogurt.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CrudRepository crudRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDruid() {
        String time = jdbcTemplate.queryForObject("select now() from dual", String.class);
        System.out.println(time);
        // String result = hibernateTemplate.execute(session -> (String) session
        //         .createNativeQuery("select now() from dual").uniqueResult());
    }
}
