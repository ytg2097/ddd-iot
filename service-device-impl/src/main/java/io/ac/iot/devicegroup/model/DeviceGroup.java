package io.ac.iot.devicegroup.model;

import io.ac.iot.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-31
 **/
@Data
@Entity
public class DeviceGroup extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String tenantId;

    private String description;

    /**
     * 员工群组与设备群组是一对多关系
     */
    private String employeeGroupId;

    public void move(String targetGroupId) {

        super.setTenantId(targetGroupId);
    }

    public void bindEmployeeGroup(String employeeGroupId){

        this.employeeGroupId = employeeGroupId;
    }

    public void unbindEmployeeGroup(){

        this.employeeGroupId = null;
    }
}
