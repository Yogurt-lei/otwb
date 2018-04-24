package com.yogurt.web.exception;

import com.mysql.jdbc.MysqlDataTruncation;
import com.yogurt.exception.BusinessException;
import com.yogurt.web.response.ResponseMessage;
import com.yogurt.web.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GlobalExceptionHandler controller层全局异常处理类
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-23 15:23
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage exception(HttpServletRequest request, HttpServletResponse response, Model model, Exception e) {
        log.error("catch undefined exception!!!", e);
        return new ResponseMessage(ResultCode.FAILED, e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("catch api-validation failed!!!",e);
        return new ResponseMessage(ResultCode.METHOD_ARGUMENT_NOT_VALID, e.getMessage());
    }

    /**
     * 处理所有业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseMessage handleBusinessException(BusinessException e){
        log.error("catch business exception!!!", e);
        return new ResponseMessage(ResultCode.BUSINESS_ERROR, e.getMessage());
    }

    /**
     * 处理所有数据库操作异常 CRUD异常
     */
    @ExceptionHandler(MysqlDataTruncation.class)
    @ResponseBody
    public ResponseMessage handleMysqlDataTruncation(BindingException e){
        log.error("catch database operation exception!!!", e);
        return new ResponseMessage(ResultCode.DAO_ERROR, e.getMessage());
    }
}
