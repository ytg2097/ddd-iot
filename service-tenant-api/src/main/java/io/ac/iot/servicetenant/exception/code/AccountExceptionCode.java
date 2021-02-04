package io.ac.iot.servicetenant.exception.code;

import common.exception.BusinessException;

/**
 * @description:  20000-29999 租户相关服务异常码
 *                          -- service-tenant        20000 - 20999
 *                          -- service-account       21000 - 21999
 * @author: yangtg
 * @create: 2020-02-27
 **/
public enum AccountExceptionCode implements BusinessException.Exception {

    ACCOUNT_NOT_FOUNT(21000, "账号不存在"),
    ACCOUNT_HAS_EXPIRED(21001, "账号已失效"),
    TENANT_HAS_EXPIRED(21002, "账号所属租户已失效"),
    EMPLOYEE_HAS_EXPIRED(21003, "账号所属员工已失效"),
    PASSWORD_ERROR(21004, "密码错误");

    public final String description;

    public final int code;

    AccountExceptionCode(int code, String description) {

        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }}
