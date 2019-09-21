package com.example.isolation_processor.http;

import java.util.Map;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public interface IHttpProcessor {

    void post(String url, Map<String,Object> params,ICallBack iCallBack);
}
