package io.ac.iot.servicetenant.common.observer;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
public interface EventListener<P> {

    void doEvent(P p);
}
