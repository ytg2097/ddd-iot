package io.ac.iot.servicedatahub.aggregation.devicemodel.capability;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-03
 **/
@Getter
@Setter
@Entity
@Table(name = "device_capability_property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 枚举值
     */
    private String enumList;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 是否必选
     */
    private Boolean required;

    /**
     * 字符串长度   只有当dataType为string时生效
     */
    private Integer maxLength;

    /**
     * 最小值   只有当dataType为int 或decimal时生效, 逻辑大于等于
     */
    private Integer min;

    /**
     * 最大值   只有当dataType为int 或decimal时生效, 逻辑小于等于
     */
    private Integer max;

    /**
     * 访问模式
     * R:可读   W:可写   E:可订阅
     * 取值范围: R, RW, RE, RWE, null
     */
    private String method;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数据类型
     */
    @Enumerated(EnumType.STRING)
    private DataType dataType;
}
