package com.example.core.bus;

/**
 * File description.
 *
 * @author dsh
 * @date 2019-09-18
 */
public class RxBus {

    private static volatile RxBus mRxBus;

    private RxBus(){ }

    private static synchronized RxBus getInstance(){
        if (mRxBus == null){
            synchronized (RxBus.class){
                if (mRxBus == null){
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }
}
