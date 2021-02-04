package io.ac.iot.device.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import common.util.ObjectUtil;
import io.ac.iot.adapter.rest.representation.DeviceListRepresentation;
import io.ac.iot.device.event.DeviceCreatedEvent;
import io.ac.iot.device.event.DeviceGroupChangedEvent;
import io.ac.iot.device.represetation.DeviceRepresentation;
import io.ac.iot.devicetag.model.DeviceTag;
import io.ac.starter.DomainEventAwareAggregate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "json",typeClass = JsonStringType.class)
public class Device extends DomainEventAwareAggregate {

    @Id
    private String id;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    public String tenantId;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String modelId;

    /**
     * 设备分组
     */
    private String deviceGroupId;

    /**
     * 运行状态
     */
//TODO   暂时存放在mysql   后续放在redis
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    /**
     * 租户方设备编号
     */
    private String serial;

    /**
     * 设备上报数据识别编号
     */
    private String uniqueCode;

    private Date onlineTime;

    private Date offlineTime;

    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private JsonNode properties;

    @ManyToMany
    @JoinTable(
            name = "device_with_tag",
            joinColumns = @JoinColumn(
                    foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
            ),
            inverseJoinColumns = @JoinColumn(
                    foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
            )
    )
    private List<DeviceTag> tags;


    public DeviceRepresentation toRepresentation(){

        return ObjectUtil.copyField(this,new DeviceRepresentation());
    }

    /**
     * 绑定设备群组
     * @param groupId
     */
    public void bindGroup(String groupId) {

        this.deviceGroupId = groupId;
        raiseEvent(new DeviceGroupChangedEvent(this));
    }

    public void postConstruct() {

        raiseEvent(new DeviceCreatedEvent(this));
    }

    public DeviceListRepresentation toListRepre() {

        return DeviceListRepresentation
                .builder()
                .createTime(createTime)
                .onlineTime(onlineTime)
                .offLineTime(offlineTime)
                .deviceId(id)
                .uniqueCode(uniqueCode)
                .serial(serial)
                .status(status)
                .build();
    }
}
