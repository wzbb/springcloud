package com.rb.ribbonprovider.audit;

import com.rb.ribbonprovider.log.RestLog;
import com.rb.util.LogUtils;
import com.rb.util.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by admin on 2020-11-9.
 */
@Aspect
@Component
public class AuditAspect {

    ThreadLocal<RestLog> restLogThreadLocal = new ThreadLocal<>();


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RestLog restLog = LogUtils.createLog(RequestUtils.getRequest(), RequestUtils.getResponse(), method, joinPoint, LocalDateTime.now());
        restLogThreadLocal.set(restLog);
    }

    @AfterReturning(pointcut = "pointCut()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RestLog restLog = restLogThreadLocal.get();

        restLog.setEndTime(LocalDateTime.now());
        Duration duration = Duration.between(restLog.getStartTime(),restLog.getEndTime());
        restLog.setMethod(method.toString());
        restLog.setResult(ret);
        restLog.setTakenTime(duration.toMillis());
        restLogThreadLocal.remove();
        LogUtils.recordInfo(restLog);

    }
}
