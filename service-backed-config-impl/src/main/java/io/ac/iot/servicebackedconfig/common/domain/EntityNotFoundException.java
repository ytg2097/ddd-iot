package io.ac.iot.servicebackedconfig.common.domain;

import common.exception.BusinessException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String entityName) {
        super(404, String.format("%s not Found",entityName));
    }
}
