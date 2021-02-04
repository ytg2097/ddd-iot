package io.ac.iot.devicetag.model;

import io.ac.iot.adapter.rest.command.DeviceTagCreateCommand;
import io.ac.iot.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Entity
@Data
public class DeviceTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String value;

    public DeviceTag(Integer id){
        this.id = id;
    }

    public DeviceTag(){}

    public static DeviceTag create(DeviceTagCreateCommand createCommand){

        DeviceTag tag = new DeviceTag();
        tag.setName(createCommand.getName());
        tag.setValue(createCommand.getValue());
        return tag;
    }
}
