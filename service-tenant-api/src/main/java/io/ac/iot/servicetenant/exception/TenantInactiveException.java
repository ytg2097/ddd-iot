package io.ac.iot.servicetenant.exception;

import common.exception.BusinessException;
import io.ac.iot.servicetenant.exception.code.AccountExceptionCode;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public class TenantInactiveException extends BusinessException {

    public TenantInactiveException() {
        super(AccountExceptionCode.TENANT_HAS_EXPIRED.code, AccountExceptionCode.TENANT_HAS_EXPIRED.description);
    }
}
