package ru.sug4chy.unitconverter.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * ru.sug4chy.unitconverter.controller.UnitConversionController.*(..))")
    public void callAtUnitController() {
    }

    @Before("callAtUnitController()")
    public void beforeControllerMethodCall(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        logger.info("Before {}, args = {}", jp.getSignature().getName(), args);
    }

    @After("callAtUnitController()")
    public void afterControllerMethodLogging(JoinPoint jp) {
        logger.info("{} completed", jp.getSignature().getName());
    }
}
