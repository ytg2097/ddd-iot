package io.ac.iot.servicetenant.tenant.factory;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
class TenantIdFactory {

    static String tenantId(){
        return gen32();
    }
}