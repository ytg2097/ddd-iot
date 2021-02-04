package io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule;

import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-13
 **/
@Entity
@Data
public class ActionRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String engineId;

    @Enumerated(EnumType.STRING)
    private ActionChannel actionChannel;

    /**
     * 设备分组id
     */
    @ElementCollection
    private List<String> deviceGroupId;

    /**
     * 设备型号
     */
    @ElementCollection
    private List<String> deviceIds;

    /**
     * 执行命令
     */
    private Integer commandId;
}
