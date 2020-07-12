package com.tua.wanchalerm.notification.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LoggingAspectJ {
    @Before("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("- Package : {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("- Method : {}", joinPoint.getSignature().getName());
    }

    @After("execution(* com.tua.wanchalerm.notification.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("- Package : {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("- Method : {}", joinPoint.getSignature().getName());
    }
}
