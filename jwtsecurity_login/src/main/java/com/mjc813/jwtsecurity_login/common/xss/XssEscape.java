package com.mjc813.jwtsecurity_login.common.xss;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)        // 멤버변수에만 선언 가능
@Retention(RetentionPolicy.RUNTIME) // 런타임에 Reflection으로 읽음
public @interface XssEscape {
}
