package io.ac.iot.devicemodel.model.capability;

import common.util.ObjectUtil;
import io.ac.iot.adapter.rest.command.DeviceCapabilityCommandModifyCommand;
import io.ac.iot.adapter.rest.command.DeviceCapabilityCreateCommand;
import io.ac.iot.adapter.rest.command.DeviceCapabilityInfoModifyCommand;
import io.ac.iot.adapter.rest.command.DeviceCapabilityPropertyModifyCommand;
import io.ac.iot.devicemodel.event.DeviceCommandModifiedEvent;
import io.ac.iot.devicemodel.event.DevicePropertyModifiedEvent;
import io.ac.starter.DomainEventAwareAggregate;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Entity
@Data
public class DeviceCapability extends DomainEventAwareAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelId;

    private String name;

    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY )
    @JoinColumn(name = "device_capability_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Command> commands;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY )
    @JoinColumn(name = "device_capability_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Event> events;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY )
    @JoinColumn(name = "device_capability_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<Property> properties;

    public static DeviceCapability create(String modelId, DeviceCapabilityCreateCommand createCommand) {

        DeviceCapability capability = new DeviceCapability();
        capability.setModelId(modelId);
        capability.setName(createCommand.getName());
        capability.setDescription(createCommand.getDescription());
        return capability;
    }

    public void modifyInfo(DeviceCapabilityInfoModifyCommand command) {

        this.name = command.getName();

        this.description = command.getDescription();
    }

    public void modifyCommandList(DeviceCapabilityCommandModifyCommand modifyCommand) {

        commands = modifyCommand.getCommandList()
                .stream()
                .map(Command::create)
                .collect(toList());

        raiseEvent(new DeviceCommandModifiedEvent(this));
    }

    public void modifyProperties(DeviceCapabilityPropertyModifyCommand command) {

        properties = command.getProperties().stream()
                .map(property -> ObjectUtil.copyField(property,new Property()))
                .collect(toList());

        raiseEvent(new DevicePropertyModifiedEvent(this));
    }
}
