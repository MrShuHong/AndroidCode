package com.example.isolation_processor.http;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public interface ICallBack {
    void onSuccess(String result);
    void onFailure(String e);
}
