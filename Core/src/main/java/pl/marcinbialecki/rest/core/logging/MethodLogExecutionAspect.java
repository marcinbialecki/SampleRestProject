package pl.marcinbialecki.rest.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Marcin Białecki on 2016-05-11.
 *
 * Method time execution logging aspect. Aspect will run on method annotated by {@link LogExecution}.
 */
@Component
@Aspect
public class MethodLogExecutionAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLogExecutionAspect.class);

    @Around("execution(* *(..)) && @annotation(LogExecution)")
    public Object logTimeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.debug("{}|{} START", new Object[] {className, methodName});
        try {
            return joinPoint.proceed(); // joinpoint execution;
        }
        finally {
            LOGGER.debug("{}|{} END Method execution time: {}",
                    new Object[] {className, methodName, (System.currentTimeMillis() - start)});
        }
    }
}
