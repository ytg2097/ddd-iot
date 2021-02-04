package io.ac.iot.adapter.rest.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ac.iot.device.model.DeviceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
@Setter
@Builder
public class DeviceListRepresentation {

    private String deviceId;

    private String serial;

    private String uniqueCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date onlineTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date offLineTime;

    private DeviceStatus status;
}
