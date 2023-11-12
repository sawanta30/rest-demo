package com.demo.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Pointcut("@annotation(Log)")
    public void logPointCut(){
    }


    @Before("logPointCut()")
    public void logMethodCalls(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("Executing {} with arguments {}",joinPoint.getSignature().getName(), Arrays.stream(args).map(Object::toString).collect(Collectors.toList()));
    }

    @Around("@annotation(Log)")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Execution completed for {} method in {} ms", proceedingJoinPoint.getSignature().getName(), System.currentTimeMillis()-startTime);
        return result;
    }
}
