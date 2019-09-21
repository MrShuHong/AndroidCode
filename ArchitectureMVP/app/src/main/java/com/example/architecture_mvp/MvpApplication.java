package com.example.architecture_mvp;

import android.app.Application;

import com.example.core.app.ProjectInit;
import com.example.isolation_processor.http.HttpHelper;
import com.example.isolation_processor.http.OkHttpProcessor;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-18
 */
public class MvpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ProjectInit.init(this)
                .withAPIHost("xxxxx")
                .configure();

        HttpHelper.init(new OkHttpProcessor());

    }
}
