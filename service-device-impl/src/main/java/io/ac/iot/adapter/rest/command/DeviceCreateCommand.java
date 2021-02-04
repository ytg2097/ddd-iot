package io.ac.iot.adapter.rest.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Data
public class DeviceCreateCommand {

    @NotBlank(message = "设备编号不能为空")
    private String serial;

    @NotBlank(message = "设备唯一标示不能为空")
    private String uniqueCode;

    @NotBlank(message = "模型ID不能为空")
    private String modelId;

    private String name;

    private String groupId;

    private List<Integer> deviceTagId;
}
