package io.ac.iot.servicedatahub.aggregation.alarm.root;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.AlarmLevel;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.NotifyType;
import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
@Entity
@Data
@Builder
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    private String tenantId;

    private String deviceModelId;

    @Enumerated(EnumType.STRING)
    private AlarmLevel level;

    private String content;

    @Enumerated(EnumType.STRING)
    private NotifyType notifyType;
}
