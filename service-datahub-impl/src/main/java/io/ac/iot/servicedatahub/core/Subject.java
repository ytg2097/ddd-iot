package io.ac.iot.servicedatahub.core;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newConcurrentMap;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public abstract class Subject<E extends Enum<E>,M> {

    private final Map<Enum<E>, List<Listener<M>>> listenerManager = newConcurrentMap();

    protected void add(E event,Listener<M> listener){

        if (!listenerManager.containsKey(event)){
            listenerManager.put(event,newArrayList());
        }
        listenerManager.get(event).add(listener);
    }

    protected void remove(E event,Listener listener){

        listenerManager.get(event).remove(listener);
    }

    protected void notifyAll(Enum<E> event, M msg){

        for (Listener<M> listener : listenerManager.get(event)) {
            listener.onMessage(msg);
        }
    }
}
