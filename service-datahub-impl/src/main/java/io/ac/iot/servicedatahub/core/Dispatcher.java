package io.ac.iot.servicedatahub.core;

import io.ac.iot.servicedatahub.core.bean.DispatchResultContext;

import java.util.function.Consumer;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface Dispatcher<T>{

    /**
     * 不回调
     * @param val
     */
    default void dispatch(T val){

        dispatch(val,v -> {});
    }

    /**
     * 需要回调
     * @param val
     * @param callback
     */
    void dispatch(T val, Consumer<DispatchResultContext<T>> callback);

}
