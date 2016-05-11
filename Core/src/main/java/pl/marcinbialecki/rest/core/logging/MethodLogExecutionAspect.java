package pl.marcinbialecki.rest.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Component
@Aspect
public class MethodLogExecutionAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLogExecutionAspect.class);

    @Around("execution(* *(..)) && @annotation(LogExecution)")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.debug("{}|{} START", new Object[] {className, methodName});
        try {
            return joinPoint.proceed(); // wykonanie joinpointu;
        }
        finally {
            LOGGER.debug("{}|{} END CZAS WYKONANIA METODY: {}",
                    new Object[] {className, methodName, (System.currentTimeMillis() - start)});
        }
    }
}
