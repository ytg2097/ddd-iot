package io.ac.iot.servicedatahub.adapter.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@RequiredArgsConstructor
public class DeviceDataConsumer {

//    private final DataProcessor dataProcessor;
//
//    private final CommandResponseHandler commandResponseHandler;
//
//    /**
//     * 设备上报数据
//     * @param message
//     */
//    @KafkaListener(topics = "device-data-report")
//    public void onDataReport(String message) {
//
//        dataProcessor.processDataPort(message);
//    }
//
//    /**
//     * 命令响应信道
//     * @param message
//     */
//    @KafkaListener(topics = "device-command-response")
//    public void onCommandResponse(String message){
//
//        commandResponseHandler.processCommandResponse(message);
//    }


}
