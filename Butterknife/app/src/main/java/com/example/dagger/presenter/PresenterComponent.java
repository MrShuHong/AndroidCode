package com.example.dagger.presenter;

import com.example.dagger.scope.UserScope;

import dagger.Component;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-15
 */
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
     Presenter providePresenter();
}
