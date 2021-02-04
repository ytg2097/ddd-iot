package io.ac.iot.servicedatahub.core.bean;

import lombok.Getter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Getter
public class DispatchResultContext<T> {

    private final T forward;

    private final Boolean state;

    private final Throwable exception;

    public DispatchResultContext(T forward, Boolean state, Throwable exception) {
        this.forward = forward;
        this.state = state;
        this.exception = exception;
    }
}
