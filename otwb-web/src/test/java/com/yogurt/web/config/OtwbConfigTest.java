package com.yogurt.web.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtwbConfigTest {

    @Autowired
    private OtwbConfig config;

    @Test
    public void getConfig() {
        Map<String, Object> config = this.config.getConfig();
        System.out.println(config);
    }
}