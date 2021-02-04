package io.ac.iot.servicedatahub.aggregation.devicemodel.root;

import common.JsonUtil;
import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DeviceCommandModifiedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DevicePropertyModifiedEvent;
import io.ac.iot.servicedatahub.aggregation.devicemodel.capability.Command;
import io.ac.iot.servicedatahub.aggregation.devicemodel.capability.Event;
import io.ac.iot.servicedatahub.aggregation.devicemodel.capability.Property;
import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Entity
@Data
@NoArgsConstructor
public class DeviceModel extends BaseEntity {

    @Id
    private String modelId;

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

    public DeviceModel(String modelId) {

        this.modelId = modelId;
    }

    public void modifyCommand(DeviceCommandModifiedEvent event) throws IOException {

        this.commands = JsonUtil.fromJsonArray(event.getCommandList(),Command.class);
    }

    public void modifyProperties(DevicePropertyModifiedEvent event) throws IOException {

        this.properties = JsonUtil.fromJsonArray(event.getProperties(),Property.class);
    }
}
