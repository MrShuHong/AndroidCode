package com.example.inject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-03
 */
public class ListenerInvocationHandler implements InvocationHandler {

    private Object context;
    private Method activityMethod;

    public ListenerInvocationHandler(Object context, Method activityMethod) {
        this.context = context;
        this.activityMethod = activityMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return activityMethod.invoke(context,args);
    }
}
