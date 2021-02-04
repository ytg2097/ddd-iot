package io.ac.iot.servicetenant.common.observer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
public abstract class EventListenerManager<E extends Enum,L extends EventListener> {

    private final Map<E, List<L>> enumListMap = new ConcurrentHashMap<>();

    protected void subscription(E event,L listener){

        if (!enumListMap.containsKey(event)){
            enumListMap.put(event,newArrayList());
        }

        enumListMap.get(event).add(listener);
    }

    protected void unsubscription(E event,L listener){

        enumListMap.get(event).remove(listener);
    }

    protected void notify(E event,Object o){

        for (L listener : enumListMap.get(event)) {
            listener.doEvent(o);
        }
    }
}
