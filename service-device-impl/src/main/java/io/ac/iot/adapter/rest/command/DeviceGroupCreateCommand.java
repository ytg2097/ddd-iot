package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-31
 **/
@Getter
@Setter
public class DeviceGroupCreateCommand {

    @NotBlank(message = "设备群组名称不能空")
    @Length(max = 15,message = "群组名称长度不得超过15个字符")
    private String name;

    private String description;

    @NotBlank
    private String tenantId;

    private String parentId;
}
