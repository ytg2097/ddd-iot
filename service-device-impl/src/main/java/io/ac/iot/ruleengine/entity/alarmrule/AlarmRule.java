package io.ac.iot.ruleengine.entity.alarmrule;

import io.ac.iot.common.domain.BaseEntity;
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
public class AlarmRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 告警名称
     */
    private String name;

    /**
     * 告警级别
     */
    @Enumerated(EnumType.STRING)
    private AlarmLevel level;

    /**
     * 告警通知类型
     */
    @Enumerated(EnumType.STRING)
    private NotifyType notifyType;

    /**
     * 告警接收方
     */
    @ElementCollection
    private List<String> receiverList;

    /**
     * 告警内容  支持占位符
     */
    private String content;

    /**
     * 告警内容中的占位符
     */
    @ElementCollection
    private List<String> placeholder;

    /**
     * 告警频率   距上次告警发生间隔时间
     */
    private Integer rate;
}
