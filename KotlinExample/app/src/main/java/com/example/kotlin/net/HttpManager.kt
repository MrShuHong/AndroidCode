package com.example.kotlin.net

import com.example.kotlin.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-23
 */
object HttpManager{

    private val mRetrofit: Retrofit
    private lateinit var mAPIService: APIService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
        mRetrofit = Retrofit.Builder()
            .baseUrl("www.baidu.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }


    fun getAPI(): APIService {
        synchronized(lock = APIService::class) {
            mAPIService = mRetrofit.create(APIService::class.java)
        }
        return this.mAPIService
    }
}