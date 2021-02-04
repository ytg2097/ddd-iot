package io.ac.iot.ruleengine.entity.orderrule;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.ac.iot.common.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Entity
@Data
@TypeDef(name = "json",typeClass = JsonStringType.class)
public class OrderRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String template;

    @ElementCollection
    private List<String> placeholder;

    /**
     * 工单产生频率  单位是分钟
     */
    private Integer rate;
}
