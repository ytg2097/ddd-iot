package io.ac.iot.device.model;

import io.ac.iot.devicemodel.model.qrcodepaserule.PropertyType;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-01
 **/
@Getter
@Setter
public class DeviceProperty {

    private PropertyType propertyType;

    private String name;
}
