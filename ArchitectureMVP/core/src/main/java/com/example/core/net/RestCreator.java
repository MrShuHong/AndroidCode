package com.example.core.net;

import com.example.core.app.ConfigKeys;
import com.example.core.app.ProjectInit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public final class RestCreator {

    private static final class RetrofitHolder {
        private static final String BASE_URL = ProjectInit.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OkHttpHolder {
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }

    /**
     * 获取对象
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
}
