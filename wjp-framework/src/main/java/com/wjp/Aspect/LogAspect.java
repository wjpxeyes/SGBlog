package com.wjp.Aspect;


import cn.hutool.json.JSONUtil;
import com.wjp.annotation.SystemLog;
import com.wjp.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(com.wjp.annotation.SystemLog)")
    public void pt() {
    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint point) throws Throwable {
        Object ret;
        try {
            handleBefore(point);
            ret = point.proceed();
            // 打印出参
            handleAfter(ret);

        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint point) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", getSystemLog(point));
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        String typeName = point.getSignature().getDeclaringTypeName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        log.info("Class Method   : {}.{}", typeName, signature.getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSONUtils.obj2json(point.getArgs()));
    }

    private String getSystemLog(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        SystemLog annotation = signature.getMethod().getAnnotation(SystemLog.class);
        return annotation.BusinessName();
    }

    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSONUtil.toJsonStr(ret));
    }
}
