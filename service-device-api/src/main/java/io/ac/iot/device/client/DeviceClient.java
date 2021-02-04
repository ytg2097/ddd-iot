package io.ac.iot.device.client;

import common.rest.RestResult;
import io.ac.iot.device.represetation.DeviceRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-26
 **/
@FeignClient(name = "service-device",path = "/device")
public interface DeviceClient {

    /**
     * find device by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    RestResult<DeviceRepresentation> findOne(@PathVariable("id")String id);


}
