package com.example.core.app;

import java.util.HashMap;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-18
 */
public class Configurator {

    private static final HashMap<Object,Object> CONFIGS = new HashMap<>();

    private Configurator(){
        CONFIGS.put(ConfigKeys.API_HOST,false);
    }

    private static class ConfiguratorHolder{
        private static Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return ConfiguratorHolder.INSTANCE;
    }

    final HashMap<Object,Object> getConfigs(){
        return CONFIGS;
    }

    public final Configurator withAPIHost(String apiHost){
        CONFIGS.put(ConfigKeys.API_HOST,apiHost);
        return this;
    }

    //配置完成
    public final void configure(){
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }

    //检查配置是否完成
    private void checkConfiguration(){
        final boolean isReady=(boolean)CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    final<T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value=CONFIGS.get(key);
        if(value==null){
            throw new NullPointerException(key.toString()+"IS NULL");
        }
        return (T)value;
    }

}
