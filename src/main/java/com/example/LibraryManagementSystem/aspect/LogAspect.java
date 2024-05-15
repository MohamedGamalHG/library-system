package com.example.LibraryManagementSystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // Pointcut expressions for specific operations
    @Pointcut("execution(* com.example.LibraryManagementSystem.services..insert(..))")
    public void bookAddition() {}

    @Pointcut("execution(* com.example.LibraryManagementSystem.services..update(..))")
    public void bookUpdate() {}

//    @Pointcut("execution(* com.example.LibraryManagementSystem.service.*.insert(..))")
//    public void patronTransaction() {}


    @AfterThrowing(pointcut = "bookAddition() || bookUpdate()", throwing = "throwable")
    public void logException(JoinPoint joinPoint, Throwable throwable) {
        logger.error("Exception occurred in method: " + joinPoint.getSignature().getName(), throwable);
    }
    @Around("bookAddition() || bookUpdate()")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        String message = buildLogMessage(joinPoint, result, endTime - startTime);
        logger.info(message);

        return result;
    }



    private String buildLogMessage(ProceedingJoinPoint joinPoint, Object result, long duration) {
        StringBuilder message = new StringBuilder();
        message.append("Method: ").append(joinPoint.getSignature().getName()).append("(");
        message.append(")");
        message.append(" - Result: ").append(result);
        message.append(" - Duration: ").append(duration).append("ms");
        return message.toString();
    }
}
