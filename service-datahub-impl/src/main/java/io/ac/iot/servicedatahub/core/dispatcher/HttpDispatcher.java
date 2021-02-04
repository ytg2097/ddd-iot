package io.ac.iot.servicedatahub.core.dispatcher;

import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import io.ac.iot.servicedatahub.core.Dispatcher;
import io.ac.iot.servicedatahub.core.bean.DispatchResultContext;
import io.ac.starter.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@Slf4j
public class HttpDispatcher implements Dispatcher<Forward> {

    @Override
    public void dispatch(Forward val, Consumer<DispatchResultContext<Forward>> callback) {

        try{
            HttpClient.httpJsonPostAsyn(val.getTarget(), val.getBody(), false, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {

                    callback.accept(new DispatchResultContext<>(val,false,e));
                }

                @Override
                public void onResponse(Call call, Response response) {

                    callback.accept(new DispatchResultContext<>(val,true,null));
                }
            });
        }catch (Exception e){
            log.warn("Http dispatch failed ForwardId:{} ,message: {}",val.getId(),e.getMessage());
        }
    }

}
