package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-05
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
