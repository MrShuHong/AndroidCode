package com.example.core.app;

import android.content.Context;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-18
 */
public final class ProjectInit {

    public static Configurator init(Context context){
        Configurator.getInstance()
                .getConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getAppContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

}
