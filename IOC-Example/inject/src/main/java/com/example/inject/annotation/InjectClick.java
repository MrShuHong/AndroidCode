package com.example.inject.annotation;

import android.view.View;

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
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnClickListener",
    listenerType = View.OnClickListener.class,
    callbackMethod = "onClick")
public @interface InjectClick {

    int[] value();
}
