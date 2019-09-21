package com.example.jetpack.livedata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * File description.
 *
 * @author dsh
 * https://github.com/MrShuHong
 * @date 2019-09-20
 */
public class LiveDataBus {

    private static Map<String, BusMutableLiveData<Object>> mLiveDataMap = new HashMap<>();

    private LiveDataBus() {

    }

    private static class Holder {
        private final static LiveDataBus INSTANCE = new LiveDataBus();
    }

    public static LiveDataBus getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized <T> BusMutableLiveData<T> with(String key, Class<T> tClass) {
        if (!mLiveDataMap.containsKey(key)) {
            mLiveDataMap.put(key, new BusMutableLiveData<Object>());
        }
        return (BusMutableLiveData<T>) mLiveDataMap.get(key);
    }


    public static class BusMutableLiveData<T> extends MutableLiveData<T> {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);

            // 问题： 如果LiveData里面有数据，在observe  owner.getLifecycle().addObserver(wrapper);
            // 造成change方法被调用。原因是new Observer 的mLastVersion比mVersion小，所以触发。

            try {
                // 1、 反射得到mLastVersion
                Class<?> liveDataClass = LiveData.class;
                Field mObserversField = liveDataClass.getDeclaredField("mObservers");
                mObserversField.setAccessible(true);

                // 反射得到SafeIterableMap class
                Object mObserversObject = mObserversField.get(this);
                Class<?> mapClass = mObserversObject.getClass();
                Method getMethod = mapClass.getDeclaredMethod("get", Object.class);
                getMethod.setAccessible(true);

                Object wrapperMapEntry = getMethod.invoke(mObserversObject, observer);
                Object observerWrapper = null;
                if (wrapperMapEntry != null && wrapperMapEntry instanceof Map.Entry) {
                    observerWrapper = ((Map.Entry) wrapperMapEntry).getValue();
                }
                if (observerWrapper == null) {
                    throw new NullPointerException("observerWrapper is null");
                }


                //LifecycleBoundObserver 对象 然后找到ObserverWrapper
                Class<?> wrapperSuperclass = observerWrapper.getClass().getSuperclass();
                Field mLastVersionField = wrapperSuperclass.getDeclaredField("mLastVersion");
                mLastVersionField.setAccessible(true);

                // 2、 反射得到mVersion
                Field mVersionField = LiveData.class.getDeclaredField("mVersion");
                mVersionField.setAccessible(true);

                // 3、 将mVersion 的值设置给mLastVersion
                Object mVersion = mVersionField.get(this);
                mLastVersionField.set(observerWrapper, mVersion);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}
