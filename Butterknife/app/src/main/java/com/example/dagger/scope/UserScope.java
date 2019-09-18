package com.example.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-15
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface UserScope {}
