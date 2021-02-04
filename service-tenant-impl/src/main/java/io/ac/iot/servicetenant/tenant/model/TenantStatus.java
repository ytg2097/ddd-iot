package io.ac.iot.servicetenant.tenant.model;


import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
public enum TenantStatus {

    /**
     * 租户到期
     */
    INACTIVE("失效"),
    /**
     * 租户到期
     */
    ACTIVE("有效"),
    /**
     * 租户创建
     */
    CREATED("已创建"),
    /**
     * 租户被锁定
     */
    LOCKED("已锁定");

    public String name;

    TenantStatus(String name) {
        this.name = name;
    }

    public static TenantStatus parse(String name) {

        if (StringUtils.isBlank(name)) {
            return null;
        }

        return Stream.of(values()).filter(status -> name.toUpperCase().equals(status.name())).findFirst().get();
    }

}
