package com.example.isolation_processor.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public class HttpHelper implements IHttpProcessor {

    private HttpHelper() {
    }


    private static class  Holder{
        private static HttpHelper INSTANCE = new HttpHelper();
    }

    public static HttpHelper obtain(){
        return Holder.INSTANCE;
    }

    private static IHttpProcessor mIHttpProcessor;

    //定义一个API,用来设置代码的接口(业务员找到一个对应的有房的人)
    public static void init(IHttpProcessor iHttpProcessor){
        mIHttpProcessor=iHttpProcessor;
    }



    @Override
    public void post(String url, Map<String, Object> params, ICallBack iCallBack) {
        IHttpProcessor iHttpProcessor = Objects.requireNonNull(mIHttpProcessor);
        String finalUrl = appendParams(url, params);
        iHttpProcessor.post(finalUrl,params,iCallBack);
    }


    public static String appendParams(String url, Map<String,Object> params) {
        if(params==null || params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder=new StringBuilder(url);
        if(urlBuilder.indexOf("?")<=0){
            urlBuilder.append("?");
        }else{
            if(!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey())
                    .append("=")
                    .append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }
    private static String encode(String str){
        try {
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
