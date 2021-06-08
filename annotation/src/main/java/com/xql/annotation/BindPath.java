package com.xql.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 */
@Target({ElementType.TYPE})//声明注解的作用域
@Retention(RetentionPolicy.CLASS) //源码期注解   编译器注解   运行期注解
public @interface BindPath {
    String key() ;
}