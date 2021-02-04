package io.ac.iot.servicedatahub.aggregation.action.root;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionChannel;
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
 * @create: 2020-11-20
 **/
@Entity
@Data
@Builder
public class Action  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String command;

    private String response;

    private LocalDateTime responseAt;

    private String deviceId;

    private String deviceModelId;

    private String tenantId;

    @Enumerated(EnumType.STRING)
    private ActionChannel actionChannel;

    private Integer commandId;
}
