package io.ac.iot.servicetenant.exception;

import common.exception.BusinessException;
import io.ac.iot.servicetenant.exception.code.AccountExceptionCode;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public class AccountPasswordErrorException extends BusinessException {

    public AccountPasswordErrorException() {
        super(AccountExceptionCode.PASSWORD_ERROR.code, AccountExceptionCode.PASSWORD_ERROR.description);
    }
}
