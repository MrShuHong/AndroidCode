package com.example.dagger.di;

import com.example.butterknife.MainActivity;
import com.example.dagger.presenter.PresenterComponent;
import com.example.dagger.scope.AppScope;

import dagger.Component;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-14
 */
@AppScope
@Component(modules = {HttpModule.class, DataBaseObjectModule.class}
        , dependencies = PresenterComponent.class)
public interface MyDataComponent {

    void injectMainActivity(MainActivity mainActivity);
}
