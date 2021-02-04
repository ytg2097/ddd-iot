package io.ac.iot.servicedatahub.core.bean;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
@Setter
public class Data {

    private String deviceId;

    /**
     *   0:  deviceSerial   1: 第三方设备Id
     */
    private Integer idType;

    /**
     * 实际数据
     */
    private JsonNode data;

}
