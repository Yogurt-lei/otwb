/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.yogurt.utils.common.web.http;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http与Servlet工具类.
 *
 * @author ThinkGem
 * @version 2014-8-19
 */
public class ServletUtils {

    public static final String DEFAULT_PARAMS_PARAM = "params";            // 登录扩展参数（JSON字符串）优先级高于扩展参数前缀
    public static final String DEFAULT_PARAM_PREFIX_PARAM = "param_";    // 扩展参数前缀

    // 定义静态文件后缀；静态文件排除URI地址
    private static String[] staticFiles;
    private static String[] staticFileExcludeUri;

    /**
     * 获取当前请求对象
     * web.xml: <listener><listener-class>
     * org.springframework.web.context.request.RequestContextListener
     * </listener-class></listener>
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            if (request == null) {
                return null;
            }
            return request;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前相应对象
     * web.xml: <filter><filter-name>requestContextFilter</filter-name><filter-class>
     * org.springframework.web.filter.RequestContextFilter</filter-class></filter><filter-mapping>
     * <filter-name>requestContextFilter</filter-name><url-pattern>/*</url-pattern></filter-mapping>
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;
        try {
            response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            if (response == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return response;
    }

    /**
     * 是否是Ajax异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.equalsAnyIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.equalsAnyIgnoreCase(ajax, "json", "xml")) {
            return true;
        }

        return false;
    }

    /**
     * 获得请求参数值
     */
    public static String getParameter(String name) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return request.getParameter(name);
    }

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header, set a fix expires date.
        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header, set a time after now.
        response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader(HttpHeaders.EXPIRES, 1L);
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        // Http 1.1 header
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

}
