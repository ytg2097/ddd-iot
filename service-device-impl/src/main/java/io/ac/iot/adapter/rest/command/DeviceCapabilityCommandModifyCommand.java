package io.ac.iot.adapter.rest.command;

import io.ac.iot.devicemodel.model.capability.DataType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Getter
@Setter
public class DeviceCapabilityCommandModifyCommand {

    @NotNull
    private Integer capabilityId;

    private List<@Valid Command> commandList;

    @Data
    public class Command{

        @NotNull
        private String name;

        private List<@Valid Param> paramFieldList;

        private List<@Valid Response> responseFieldList;
    }

    @Getter
    @Setter
    public class Param{

        @NotNull
        private String name;

        @NotNull
        private DataType dataType;

        @NotNull
        private Boolean required;

        private Integer min;

        private Integer max;

        private Integer maxLength;

        private String unit;

        private List<String> enumList;
    }

    @Getter
    @Setter
    public class Response{

        @NotNull
        private String name;

        @NotNull
        private DataType dataType;

        private String unit;
    }

}
