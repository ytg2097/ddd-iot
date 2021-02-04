package io.ac.iot.servicetenant.exception;

import common.exception.BusinessException;
import io.ac.iot.servicetenant.exception.code.AccountExceptionCode;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public class AccountNotExistsException extends BusinessException {

    public AccountNotExistsException() {
        super(AccountExceptionCode.ACCOUNT_NOT_FOUNT.code, AccountExceptionCode.ACCOUNT_NOT_FOUNT.description);
    }
}
