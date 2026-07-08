package com.mjc813.jwtsecurity_login.common.xss;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = XssErrorValidator.class)
public @interface XssError {
	String message() default "XSS 공격이 감지되었습니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

