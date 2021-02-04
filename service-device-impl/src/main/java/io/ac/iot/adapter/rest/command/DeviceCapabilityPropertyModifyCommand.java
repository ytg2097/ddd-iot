package io.ac.iot.adapter.rest.command;

import io.ac.iot.devicemodel.model.capability.DataType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Getter
@Setter
@ApiModel("能力属性")
public class DeviceCapabilityPropertyModifyCommand {

    @NotNull
    @ApiModelProperty("能力ID")
    private Integer capabilityId;

    private List<@Valid Property> properties;

    @Getter
    @Setter
    public class Property{
        @ApiModelProperty("枚举列表")
        private String enumList;

        @NotNull
        private String name;

        @NotNull
        private Boolean required;

        private Integer maxLength;

        private Integer min;

        private Integer max;

        private String method;

        private String unit;

        @NotNull
        private DataType dataType;
    }

}
