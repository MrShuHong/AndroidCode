package com.example.core.net;

import com.example.core.net.callback.IError;
import com.example.core.net.callback.IFailure;
import com.example.core.net.callback.IRequest;
import com.example.core.net.callback.ISuccess;
import com.example.core.net.callback.RequestCallbacks;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-19
 */
public class RestClient {

    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    //上传下载
    private final File FILE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;

    public RestClient(HashMap<String, Object> PARAMS, String URL,
                      IRequest REQUEST, ISuccess SUCCESS,
                      IFailure FAILURE, IError ERROR,
                      RequestBody BODY, File FILE,
                      String DOWNLOAD_DIR, String EXTENSION,
                      String FILENAME) {
        this.PARAMS = PARAMS;
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.BODY = BODY;
        this.FILE = FILE;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.FILENAME = FILENAME;
    }


    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(SUCCESS, ERROR, REQUEST, FAILURE);
    }

    private void request(HttpMethod httpMethod) {
        RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //开始进行网络访问
        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }

    //各种请求(给用户使用的)
    //各种请求
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }


    /*public final void download(){
        new DownloadHandler(PARAMS,URL,REQUEST,
                SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,
                EXTENSION,FILENAME)
                .handleDownload();
    }*/
}
