/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.yogurt.utils.common.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源供给类
 *
 * @author ThinkGem
 * @version 2016-9-16
 */
@Slf4j
public class ResourceUtils extends org.springframework.util.ResourceUtils {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * 获取资源加载器（可读取jar内的文件）
     *
     * @author ThinkGem
     */
    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * 获取ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return resourceLoader.getClassLoader();
    }

    /**
     * 获取资源加载器（可读取jar内的文件）
     */
    public static Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }

    /**
     * 获取资源文件流（用后记得关闭）
     *
     * @author ThinkGem
     */
    public static InputStream getResourceFileStream(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        return resource.getInputStream();
    }

    /**
     * 获取资源文件内容
     *
     * @author ThinkGem
     */
    public static String getResourceFileContent(String location) {
        String content = null;
        try (InputStream is = ResourceUtils.getResourceFileStream(location)) {
            content = IOUtils.toString(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * Spring 搜索资源文件
     *
     * @author ThinkGem
     */
    public static Resource[] getResources(String locationPattern) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(locationPattern);
            return resources;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
