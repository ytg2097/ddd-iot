package io.ac.iot.servicelog.tenantoperationlog;

import io.ac.iot.servicelog.tenantoperationlog.event.TenantOperationFinishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static io.ac.iot.servicelog.tenantoperationlog.TenantOperationLogEventConsumer.OperationLogInput.TENANT_OPERATION_LOG_IN;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-24
 **/
@Component
public class TenantOperationLogEventConsumer {

    @Autowired
    private TenantOperationLogService service;

    @StreamListener(TENANT_OPERATION_LOG_IN)
    public void on(@Payload TenantOperationFinishEvent event){

        service.save(event);
    }

    public interface OperationLogInput {

        String TENANT_OPERATION_LOG_IN = "tenant-operation-log";
        @Input(TENANT_OPERATION_LOG_IN)
        SubscribableChannel input();
    }
}
