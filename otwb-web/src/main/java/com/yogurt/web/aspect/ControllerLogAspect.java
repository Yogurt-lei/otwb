package com.yogurt.web.aspect;

import com.yogurt.utils.common.web.http.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ControllerLogAspect controller日志切面
 *
 * @author Yogurt_lei
 * @date 2018-04-19 19:37
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     * 定义切入点 所有的Controller
     *
     * @author Yogurt_lei
     * @date 2018-04-19 19:42
     */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    /**
     * 定义切面方法 所有的public方法
     */
    @Pointcut("execution(public * *(..))")
    public void methodPointcut() {
    }

    @Before("controller() && methodPointcut()")
    public void beforeMethodStart() {
        stopWatch.set(new StopWatch());
    }

    /**
     * 正常调用后写入日志
     */
    @AfterReturning(value = "controller() && methodPointcut()", returning = "value")
    public void afterMethodReturning(JoinPoint point, Object value) {
        String controllerName = point.getThis().getClass().getSimpleName();
        if (StringUtils.indexOf(controllerName, "$$") > 1) {
            controllerName = StringUtils.substringBefore(controllerName, "$$");
        }

        final String serviceMethod = controllerName + "." + point.getSignature().getName();
        long totalTimeMillis = stopWatch.get().getTotalTimeMillis();
        //获取统计日志所需参数
        final HttpServletRequest request = ServletUtils.getRequest();
        final String userAgent = request.getHeader("User-Agent");

        executor.execute(() -> {
            synchronized (this) {
                // TODO by Yogurt_lei on 2018-04-19 22:49 : 日志写入数据库
            }
        });
    }
}
