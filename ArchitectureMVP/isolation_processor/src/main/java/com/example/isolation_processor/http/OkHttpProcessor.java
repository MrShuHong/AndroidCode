package com.example.isolation_processor.http;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public class OkHttpProcessor implements IHttpProcessor {

    private OkHttpClient mOkHttpClient;
    private Handler mHttpHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        mHttpHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack iCallBack) {

        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHttpHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.onFailure(HttpErrorMessage.REQUEST_FAILED);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    final String responseResult = Objects.requireNonNull(response.body()).string();
                    mHttpHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            iCallBack.onSuccess(responseResult);
                        }
                    });
                } else {
                    mHttpHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            iCallBack.onFailure(HttpErrorMessage.EMPTY_DATA);
                        }
                    });
                }

            }
        });

    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        return builder.build();
    }
}
