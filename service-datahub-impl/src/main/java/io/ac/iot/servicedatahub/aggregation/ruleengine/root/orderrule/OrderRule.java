package io.ac.iot.servicedatahub.aggregation.ruleengine.root.orderrule;

import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Data
@Entity
public class OrderRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String engineId;

    private String template;

    @ElementCollection
    private List<String> placeholder;

    private Integer rate;
}
