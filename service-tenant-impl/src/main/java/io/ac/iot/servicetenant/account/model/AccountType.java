package io.ac.iot.servicetenant.account.model;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public enum AccountType {

    PLATFORM,
    APP;

    public static AccountType parse(String name) {

        if (StringUtils.isBlank(name)) {
            return null;
        }

        return Stream.of(values()).filter(status -> name.toUpperCase().equals(status.name())).findFirst().get();
    }

}
