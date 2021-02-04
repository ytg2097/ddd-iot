package io.ac.iot.servicedatahub.exception;

/**
 * @description:  20000-29999 租户相关服务异常码
 *                          -- service-datahub        20000 - 20999
 * @description:  30000-39999 设备相关服务异常码
 *                          -- service-datahub        30000 - 30999
 *                          -- service-datahub-data   31000 - 31999
 *
 * @description:  40000-49999 工单相关服务异常码
 *                          -- service-order         40000 - 40999
 *                          -- service-order-history 41000 - 41999
 * @description:  50000-59999 通知相关服务异常码
 *                          -- service-notification 50000 - 50999
 * @author: yangtg
 * @create: 2020-02-27
 **/
public enum TenantExceptionCode {

    TENANT_NOT_FOUNT(20000, "租户不存在");

    public final String description;

    public final int code;

    TenantExceptionCode(int code, String description) {

        this.code = code;
        this.description = description;
    }

}
