package com.yogurt.web.controller;

import com.yogurt.web.constant.SessionConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-22 20:11
 */
public class BaseController {
    /**
     * 获取登陆用户Id
     */
    protected String getCurrentUserId(HttpServletRequest request){
        return (String)request.getSession().getAttribute(SessionConstants.CURRENT_USER_ID);
    }
}
