package io.ac.iot.devicemodel.model;

import io.ac.iot.common.domain.BaseEntity;
import io.ac.iot.adapter.rest.command.DeviceModelCreateCommand;
import io.ac.starter.util.RequestHeadHolder;
import lombok.Data;

import javax.persistence.*;

import static common.util.ObjectUtil.copyField;
import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@Data
@Entity
public class DeviceModel extends BaseEntity {

    @Id
    private String id;

    /**
     * 型号代码
     */
    private String model;

    /**
     * 厂家id
     */
    private String manufacturerId;

    /**
     * 厂家名字
     */
    private String manufacturerName;

    /**
     * 协议类型
     */
    private String protocolType;

    /**
     * 样本图片
     */
    private String pic;

    /**
     * 描述
     */
    private String description;

    /**
     * 租户id
     */
    private String tenantId;

}
