package io.ac.iot.servicedatahub.core;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
public interface Listener<MSG> {

    void onMessage(MSG message);
}
