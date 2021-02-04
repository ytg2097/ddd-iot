package io.ac.iot.servicelog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 操作类型
     * @return
     */
    Type type();

    /**
     * 具体操作
     * @return
     */
    String operating();

    /**
     * 操作对象
     * @return
     */
    Target target();

    enum Type {

        QUERY,
        OPERATE,
        SAFETY
    }

    @Retention(RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target({})
    @interface Target{

        Origin origin();

        String name();

        enum Origin{
            METHOD_ARGS,
            REQUEST_HEAD,
            REQUEST_PARAM,
            REQUEST_BODY,
            CUSTOMIZE
        }
    }


}
