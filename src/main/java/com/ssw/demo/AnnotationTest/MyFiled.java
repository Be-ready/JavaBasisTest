package com.ssw.demo.AnnotationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解（作用于字段上，字段的描述和长度）
 */
@Target(ElementType.FIELD)  // 注解用于字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFiled {
    String description();

    int length();
}
