package io.ac.iot.servicetenant.employeegroup.model;

import io.ac.iot.servicetenant.employeegroup.command.EmployeeGroupCreateCommand;
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
public class EmployeeGroupFactory {

    private String groupId(){
        return gen32();
    }

    public EmployeeGroup newInstance(EmployeeGroupCreateCommand createCommand){

        EmployeeGroup entity = new EmployeeGroup();
        entity.setId(groupId());
        entity.setName(createCommand.getName());
        entity.setParentId(parentId(createCommand));
        entity.setTenantId(createCommand.getTenantId());
        entity.setDescription(createCommand.getDescription());
        entity.setValid(Boolean.TRUE);
        return entity;
    }

    private String parentId(EmployeeGroupCreateCommand createCommand) {

        return isNull(createCommand.getParentId())? createCommand.getTenantId() : createCommand.getParentId();
    }
}