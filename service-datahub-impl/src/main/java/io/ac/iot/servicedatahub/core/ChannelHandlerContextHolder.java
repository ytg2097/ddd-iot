package io.ac.iot.servicedatahub.core;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
@Component
public class ChannelHandlerContextHolder {

    private final Map<String, ChannelHandlerContext> holder = new HashMap<>();

    public void refresh(String key,ChannelHandlerContext ctx){

        holder.compute(key,(deviceId,oldCtx) -> {

            if (oldCtx != null){
                oldCtx.close();
                return ctx;
            }
            return ctx;
        });
    }

    public ChannelHandlerContext get(String key){

        return holder.get(key);
    }

    public void clear(){

        holder.clear();
    }
}
