package com.example.inject;

import android.util.Log;
import android.view.View;

import com.example.inject.annotation.ContentView;
import com.example.inject.annotation.EventBase;
import com.example.inject.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-03
 */
public class InjectUtils {

    private static final String TAG = "InjectUtils";

    public static void init(Object context) {
        injectLayout(context);
        injectView(context);
        injectListener(context);
    }

    private static void injectListener(Object context) {

        /**
         * btn_01.setOnClickListener(new View.OnClickListener() {
         *             @Override
         *             public void onClick(View v) {
         *
         *             }
         *         });
         */

        Class<?> clazz = context.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //得到方法上的注解类
                Class<? extends Annotation> annotationType = annotation.annotationType();

                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                //获取注解类上是否有被EventBase 注解
                if (eventBase != null) {
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String callbackMethod = eventBase.callbackMethod();

                    try {
                        Method injectAnnotationMethod = annotationType.getMethod("value");
                        int[] ids = (int[]) injectAnnotationMethod.invoke(annotation);
                        for (int id : ids) {
                            //拿到注解身上的Id ，调用findViewById
                            Method findViewById = clazz.getMethod("findViewById", int.class);
                            View view = (View) findViewById.invoke(context, id);
                            if (view != null) {
                                method.setAccessible(true);
                                ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler(context, method);

                                Object proxyObj = Proxy.newProxyInstance(annotationType.getClassLoader(), new Class[]{listenerType}, invocationHandler);

                                Class<? extends View> viewClass = view.getClass();
                                Method listenerSetterMethod = viewClass.getMethod(listenerSetter, listenerType);
                                listenerSetterMethod.invoke(view, proxyObj);
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }

    /**
     * 注入View 实现findviewbyId
     *
     * @param context
     */
    private static void injectView(Object context) {
        Class<?> clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                field.setAccessible(true);
                InjectView injectView = field.getAnnotation(InjectView.class);
                if (injectView != null) {
                    int viewID = injectView.value();
                    if (viewID != -1) {
                        try {
                            Method findViewById = clazz.getMethod("findViewById", int.class);
                            View view = (View) findViewById.invoke(context, viewID);
                            field.set(context, view);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 注入layout布局
     *
     * @param context
     */
    private static void injectLayout(Object context) {
        Class<?> clazz = context.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutID = contentView.value();
            //调用setContentView方法

            if (layoutID == -1) {
                Log.e(TAG, "ContentView 注解没有设置value");
                return;
            }

            try {
                Method setContentView = clazz.getMethod("setContentView", int.class);
                setContentView.invoke(context, layoutID);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
