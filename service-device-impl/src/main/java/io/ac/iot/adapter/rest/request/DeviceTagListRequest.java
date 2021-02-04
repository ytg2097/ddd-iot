package io.ac.iot.adapter.rest.request;

import common.rest.BasePageQueryParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Getter
@Setter
public class DeviceTagListRequest extends BasePageQueryParam {

    private String name;

    private String value;
}
