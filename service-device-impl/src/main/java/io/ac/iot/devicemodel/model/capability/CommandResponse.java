package io.ac.iot.devicemodel.model.capability;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-01
 **/
@Data
public class CommandResponse {

    private String name;

    private DataType dataType;

    private String unit;
}
