package com.inventory.manager.service.utils.idempotency;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.manager.service.dao.IdempotencyRepository;
import com.inventory.manager.service.datatypes.IdempotencyKey;
import com.inventory.manager.service.repository.Idempotence;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Aspect
@Slf4j
public class IdempotenceAspect {

    @Autowired
    private IdempotencyRepository idempotencyRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(@com.inventory.manager.service.utils.idempotency.Idempotent  * *(..))")
    public void isAnnotated() {
    }


    @Around("isAnnotated()")
    @Transactional
    public Object method(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        IdempotencyKey idempotencyKey = null;

        for(Object signatureArgs: args) {
            if( signatureArgs instanceof IdempotencyKey) {
                idempotencyKey = (IdempotencyKey) signatureArgs;
                break;
            }
        }

        if(idempotencyKey == null) {
            throw new RuntimeException("Idempotency key is missing");
        }

        String requestName = proceedingJoinPoint.getSignature().getName();

        Idempotence previousResponse = idempotencyRepository.findByRequestIdAndRequestNameAndRequestedBy(
                idempotencyKey.getRequestId(), requestName, idempotencyKey.getRequestedBy());

        if(previousResponse != null) {
            log.info("Request Id already processed: " + idempotencyKey.getRequestId() + " for method: "
                    + requestName + " for user " + idempotencyKey.getRequestedBy());
            return previousResponse.getResponseBody();
        }

        Object returnValue = proceedingJoinPoint.proceed();

        Idempotence request = new Idempotence();
        request.setRequestId(idempotencyKey.getRequestId());
        request.setRequestedBy(idempotencyKey.getRequestedBy());
        request.setRequestName(requestName);
        request.setResponseBody(returnValue);

        idempotencyRepository.save(request);

        return returnValue;

    }
}
