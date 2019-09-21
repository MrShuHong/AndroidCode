package com.example.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-20
 */
public class PageLifecycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.d("Tag","start");
    }
}
