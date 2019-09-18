package com.example.dagger.di;

import com.example.dagger.entity.DataBaseObject;

import dagger.Module;
import dagger.Provides;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-14
 */
@Module
public class DataBaseObjectModule {

    @Provides
    public DataBaseObject providerDataBaseObject(){
        return new DataBaseObject();
    }
}
