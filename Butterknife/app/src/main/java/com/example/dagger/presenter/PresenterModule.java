package com.example.dagger.presenter;

import com.example.dagger.scope.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-15
 */
@Module
public class PresenterModule {

    @Provides
    public Presenter providerPresenter(){
        return new Presenter();
    }
}
