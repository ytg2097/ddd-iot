package io.ac.iot.devicegroup.model;

import io.ac.iot.adapter.rest.command.DeviceGroupCreateCommand;
import org.springframework.stereotype.Component;

import static common.util.UUIDGenerator.gen32;
import static java.util.Objects.isNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
@Component
public class DeviceGroupFactory {

    private String groupId(){
        return gen32();
    }

    public DeviceGroup newInstance(DeviceGroupCreateCommand createCommand){

        DeviceGroup entity = new DeviceGroup();
        entity.setId(groupId());
        entity.setName(createCommand.getName());
        //todo
//        entity.setParentId(parentId(createCommand));
        entity.setTenantId(createCommand.getTenantId());
        entity.setDescription(createCommand.getDescription());
        return entity;
    }

    private String parentId(DeviceGroupCreateCommand createCommand) {

        return isNull(createCommand.getParentId())? createCommand.getTenantId() : createCommand.getParentId();
    }
}