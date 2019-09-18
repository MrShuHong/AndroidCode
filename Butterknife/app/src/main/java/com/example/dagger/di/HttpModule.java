package com.example.dagger.di;

import com.example.dagger.entity.HttpObject;
import com.example.dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-14
 */
@Module
public class HttpModule {

    String url;

    public HttpModule(String url) {
        this.url = url;
    }

    @AppScope
    @Provides
    public HttpObject providerHttpObject(){
        return new HttpObject(url);
    }
}
