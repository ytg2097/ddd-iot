package io.ac.iot.devicemodel.model.capability;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import common.util.ObjectUtil;
import io.ac.iot.adapter.rest.command.DeviceCapabilityCommandModifyCommand;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-03
 **/
@Data
@Entity
@Table(name = "device_capability_command")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private List<CommandParam> params;

    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private List<CommandResponse> response;

    public static Command create(DeviceCapabilityCommandModifyCommand.Command c) {

        Command command = new Command();

        command.name = c.getName();

        command.params = c.getParamFieldList().stream()
                .map(field -> ObjectUtil.copyField(field,new CommandParam()))
                .collect(toList());

        command.response = c.getResponseFieldList().stream()
                .map(field -> ObjectUtil.copyField(field,new CommandResponse()))
                .collect(toList());
        return command;
    }
}
