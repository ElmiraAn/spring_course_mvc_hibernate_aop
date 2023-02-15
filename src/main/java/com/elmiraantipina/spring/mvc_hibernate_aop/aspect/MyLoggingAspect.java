package com.elmiraantipina.spring.mvc_hibernate_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect {

    @Around("execution(* com.elmiraantipina.spring.mvc_hibernate_aop.dao.*.*(..))")//на все классы пакета dao,
    // на все методы этих классов, с любым количеством параметров
    public Object aroundAllRepositoryMethodsAdvice(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();//получаем данные о сигнатуре метода
        String methodName = methodSignature.getName();//получаем имя метода
        System.out.println("Begin of "+methodName);


        //внутри advice (при исп-ии around) должны сами запустить метод с помощью proceed
        Object targetMethodResult = proceedingJoinPoint.proceed();

        System.out.println("End of "+methodName);
        return targetMethodResult;
    }
}
