package com.yogurt.web.exception;

import com.yogurt.model.vo.ResponseMessage;
import com.yogurt.model.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
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
        log.error("catch exception!!!", e);
        return new ResponseMessage(ResultCode.FAILED, e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("validation failed!!!",e);
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
}
