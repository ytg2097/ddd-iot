package io.ac.iot.servicetenant.exception;

import common.exception.BusinessException;
import io.ac.iot.servicetenant.exception.code.AccountExceptionCode;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public class EmployeeInactiveException extends BusinessException {

    public EmployeeInactiveException() {
        super(AccountExceptionCode.EMPLOYEE_HAS_EXPIRED.code, AccountExceptionCode.EMPLOYEE_HAS_EXPIRED.description);
    }
}
