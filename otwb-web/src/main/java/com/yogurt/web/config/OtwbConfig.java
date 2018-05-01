package com.yogurt.web.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * OtwbConfig 读取yaml定义的otwb相关属性
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-05-01 20:44
 */
@Getter
@Component
@ConfigurationProperties(prefix = "otwb")
public class OtwbConfig {
    private Map<String, Object> config = new HashMap<>();

}
