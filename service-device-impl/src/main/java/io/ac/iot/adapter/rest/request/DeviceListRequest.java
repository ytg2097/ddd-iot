package io.ac.iot.adapter.rest.request;

import common.rest.BasePageQueryParam;
import io.ac.iot.device.model.DeviceStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
@Setter
public class DeviceListRequest extends BasePageQueryParam {

    private String deviceModelId;

    private String searchValue;

    private DeviceStatus status;
}
