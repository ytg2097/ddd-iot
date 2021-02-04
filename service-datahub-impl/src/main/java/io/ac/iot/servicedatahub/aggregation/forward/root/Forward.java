package io.ac.iot.servicedatahub.aggregation.forward.root;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardTargetType;
import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Forward extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发送内容
     */
    private String body;

    @Enumerated(EnumType.STRING)
    private ForwardTargetType forwardTargetType;

    private String target;

    private String deviceId;

    private String deviceModelId;

    private String tenantId;

    /**
     * 发送状态
     */
    private Boolean state;
}
