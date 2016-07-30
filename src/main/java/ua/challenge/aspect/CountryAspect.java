package ua.challenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by d.bakal on 6/26/2016.
 */
@Aspect
@Component
public class CountryAspect {
    @Pointcut("execution(* ua.challenge.controller.booking.CountryController.save(..))") private void methodSave() {
    }

    @Before("execution(* ua.challenge.controller.booking.CountryController.save(..))")
    private void beforeMethod() {
        System.out.println("Before: Executing Advice on method save()");
    }

    @After("execution(* ua.challenge.controller.booking.CountryController.save(..))")
    private void afterMethod() {
        System.out.println("After: Executing Advice on method save()");
    }

    @AfterReturning("methodSave()")
    private void afterReturningMethod() {
        System.out.println("AfterReturning: Executing Advice on method save()");
    }

    @AfterThrowing("methodSave()")
    private void afterThrowing(JoinPoint joinPoint) {
        System.out.println("AfterThrowing: Executing Advice on method " + joinPoint.getSignature().getName());
    }

    @Around("methodSave()")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Before invoking save() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After invoking save() method");
        return value;
    }
}
