package com.mymall.framework.aspect;

import com.mymall.framework.exception.customizedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
@Order(2)
@Slf4j
public class RedisAspect {

    @Around("execution(* com.mymall.framework.utils.RedisUtils.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;
        log.info("-----redis aspect-");
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("redis error: {}",e.getMessage());
            throw new customizedException("Redis services are not available");
        }
        return result;
    }
}
