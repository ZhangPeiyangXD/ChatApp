package com.itzpy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalInterceptor {
    // 是否需要检查登录
    boolean checkLogin() default true;
    // 是否需要检查管理员身份
    boolean checkAdmin() default false;
}
