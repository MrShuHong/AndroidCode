package com.example.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    /**
     * 订阅方法名
     */
   String listenerSetter();


    /**
     * 事件监听的类型
     */
   Class<?> listenerType();

    /**
     * 回调方法名
     */
    String callbackMethod();
}
