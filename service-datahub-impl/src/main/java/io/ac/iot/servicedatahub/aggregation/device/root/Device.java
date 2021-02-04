package io.ac.iot.servicedatahub.aggregation.device.root;

import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceGroupChangedEvent;
import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Device extends BaseEntity {

    @Id
    private String deviceId;

    private String tenantId;

    private String uniqueCode;

    private String groupId;

    private String modelId;

    public void modifyGroupId(DeviceGroupChangedEvent event) {

        this.groupId = event.getGroupId();
    }
}
