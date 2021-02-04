package io.ac.iot.servicedatahub.aggregation.order.root;

import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Entity
@Data
@Builder
@Table(name = "`order`")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String deviceId;

    private String deviceModelId;

    private String tenantId;

}
