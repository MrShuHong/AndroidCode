package com.example.core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public class RequestCallbacks implements Callback<String> {
    private final ISuccess mISuccess;
    private final IError mIError;
    private final IRequest mIRequest;
    private final IFailure mIFailure;

    public RequestCallbacks(ISuccess ISuccess, IError IError,
                            IRequest IRequest, IFailure IFailure) {
        mISuccess = ISuccess;
        mIError = IError;
        mIRequest = IRequest;
        mIFailure = IFailure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (mISuccess != null) {
                    mISuccess.onSuccess(response.body());
                }
            }
        } else {
            if (mIError != null) {
                mIError.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (mIFailure != null) {
            mIFailure.onFailure();
        }
        if (mIRequest != null) {
            mIRequest.onRequestEnd();
        }
    }
}
