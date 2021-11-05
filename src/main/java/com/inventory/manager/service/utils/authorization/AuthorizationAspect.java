package com.inventory.manager.service.utils.authorization;

import com.inventory.manager.service.utils.context.SampleContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.ws.rs.BadRequestException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
public class AuthorizationAspect {

    @Pointcut("execution(@com.inventory.manager.service.utils.authorization.Authorization * *(..))")
    public void isAnnotated() {

    }

    @Around("isAnnotated()")
    public Object authorize(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        Authorization authorization = method.getAnnotation(Authorization.class);
        log.info(authorization.value()[0] + " : " + SampleContextHolder.get().toString());

        if (Arrays.stream(authorization.value()).anyMatch(a -> a.equalsIgnoreCase(SampleContextHolder.get().getName()))) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new BadRequestException("User Not Authorized");
        }
    }
}
