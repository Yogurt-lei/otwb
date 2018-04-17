package com.yogurt.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesUtil
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 21:51
 */
@Component
public class PropertiesUtil {
    private static Properties props;

    public PropertiesUtil() {
        try {
            Resource resource = new ClassPathResource("/application.properties");
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性值
     */
    public static String getProperty(String key) {
        return props == null ? null : props.getProperty(key);
    }

    /**
     * 获取属性值 未获取到范围默认值
     */
    public static String getProperty(String key, String defaultValue) {
        return props == null ? null : props.getProperty(key, defaultValue);
    }
}
