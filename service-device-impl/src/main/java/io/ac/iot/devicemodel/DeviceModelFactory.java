package io.ac.iot.devicemodel;

import io.ac.iot.adapter.rest.command.DeviceModelCreateCommand;
import io.ac.iot.devicemodel.model.DeviceModel;
import io.ac.starter.util.RequestHeadHolder;

import static common.util.ObjectUtil.copyField;
import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
public class DeviceModelFactory {

    public static DeviceModel create(DeviceModelCreateCommand createCommand){

        DeviceModel entity = copyField(createCommand,new DeviceModel());
        entity.setId(gen32());
        entity.setTenantId(RequestHeadHolder.get(RequestHeadHolder.RequestHead.TENANT_ID));
        return entity;
    }
}
