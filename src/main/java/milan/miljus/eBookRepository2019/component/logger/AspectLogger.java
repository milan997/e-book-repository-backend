package milan.miljus.eBookRepository2019.component.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by milan.miljus on 2019-07-03 23:34.
 */
@Aspect
@Component
@Slf4j
public class AspectLogger {

    private final String NOT_LOGGING_MESSAGE = "not logging args for this class";

    @Pointcut( "    execution(* milan.miljus.eBookRepository2019.service..*.*(..))"
            +  " || execution(* milan.miljus.eBookRepository2019.component..*.*(..))"
            +  " || execution(* milan.miljus.eBookRepository2019.controller.*.*(..))"
    )
    private void methodCall() {}

    @Before("methodCall() && !@target(milan.miljus.eBookRepository2019.component.logger.LoggerIgnore)")
    public void beforeMethodCall(JoinPoint jp){
        logMethodCall(jp, true);
    }

    @Before("methodCall() && @target(milan.miljus.eBookRepository2019.component.logger.LoggerIgnore)")
    public void beforeNoLoggingMethodCall(JoinPoint jp) {
        logMethodCall(jp, false);
    }

    private void logMethodCall(JoinPoint jp, boolean logArgs) {
        final String[] argNames = ((CodeSignature) jp.getSignature()).getParameterNames();
        final String[] argValues = Arrays.stream(jp.getArgs()).map(String::valueOf).toArray(String[]::new);

        String args = "";
        if (logArgs) {
            for (int i=0; i<argNames.length; i++) {
                args += String.format("%s: %s; ", argNames[i], argValues[i]);
            }
        } else {
            args = NOT_LOGGING_MESSAGE;
        }

        final Class sourceClass = jp.getTarget().getClass();
        final String className = sourceClass.getName();
        final String methodName = jp.getSignature().getName();

        log.info("{}.{}() --- args: {}", className, methodName, args);
    }
}
