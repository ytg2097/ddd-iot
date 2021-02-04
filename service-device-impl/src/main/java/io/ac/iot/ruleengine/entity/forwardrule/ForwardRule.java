package io.ac.iot.ruleengine.entity.forwardrule;

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
public class ForwardRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 转发目标  目前只有http
     */
    @Enumerated(EnumType.STRING)
    private ForwardTargetType targetType;

    /**
     * 目标地址
     */
    private String target;

    /**
     * 转发内容  要求json
     */
    private String content;

    /**
     * 占位符
     */
    @ElementCollection
    private List<String> placeholder;
}
