package io.ac.iot.servicedatahub.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public class BeanUtil {

    private static ApplicationContext context;

    public static <T> Collection<T> getBeansOfType(Class<T> clazz){

        return context.getBeansOfType(clazz).values();
    }

    @Component
    @Lazy(false)
    @Order(-2147483647)
    public static class ContextLoadedListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

            context = contextRefreshedEvent.getApplicationContext();
        }
    }
}
