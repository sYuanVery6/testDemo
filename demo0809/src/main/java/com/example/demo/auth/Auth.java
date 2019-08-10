package com.example.demo.auth;

import java.lang.annotation.*;

@Inherited//子类自动继承
@Target({ElementType.TYPE,ElementType.METHOD})//注解存放位置
@Retention(RetentionPolicy.RUNTIME)//注解生命周期
@Documented//生成注解文档
public @interface Auth {
    String user() default "";
}
