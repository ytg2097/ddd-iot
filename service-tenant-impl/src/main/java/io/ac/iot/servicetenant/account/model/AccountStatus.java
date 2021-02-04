package io.ac.iot.servicetenant.account.model;


import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
public enum AccountStatus {

    INACTIVE("失效"),
    ACTIVE("有效");

    public String name;

    AccountStatus(String name) {
        this.name = name;
    }

    public static AccountStatus parse(String name) {

        if (StringUtils.isBlank(name)) {
            return null;
        }

        return Stream.of(values()).filter(status -> name.toUpperCase().equals(status.name())).findFirst().get();
    }

}
