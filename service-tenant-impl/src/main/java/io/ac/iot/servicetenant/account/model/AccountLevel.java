package io.ac.iot.servicetenant.account.model;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-29
 **/
public enum AccountLevel {

    /**
     * 每个租户有且只有一个ROOT账号
     */
    ROOT,
    /**
     * 租户自己创建的账号
     */
    ORDINARY;
}
