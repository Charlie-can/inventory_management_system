package com.backend.aspect;

import com.backend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class LogAspect {


    @AfterReturning(value = "execution(* com.backend.service..* (..))", returning = "result")
    public void LogoutReturn(JoinPoint joinPoint, Result result) {

        log.info(joinPoint.getSignature().getName());
        if (result != null)
            log.info(result.toString());

    }

}
