package io.ac.iot.servicetenant.exception.code;

/**
 * @description:  20000-29999 租户相关服务异常码
 *                          -- tenant        20000 - 20999
 *                          -- account        21000 - 21999
 * @description:  30000-39999 设备相关服务异常码
 *                          -- service-device        30000 - 30999
 *                          -- service-device-data   31000 - 31999
 *
 * @description:  40000-49999 工单相关服务异常码
 *                          -- service-order         40000 - 40999
 *                          -- service-order-history 41000 - 41999
 * @description:  50000-59999 通知相关服务异常码
 *                          -- service-notification 50000 - 50999
 * @author: yangtg
 * @create: 2020-02-27
 **/
public enum EmployeeExceptionCode {

    EMPLOYEE_NOT_FOUNT(21000, "员工不存在"),
    EMPLOYEE_HAS_EXPIRED(21001, "员工已失效"),

    EMPLOYEE_GROUP_NOT_FOUNT(21002, "群组不存在");

    public final String description;

    public final int code;

    EmployeeExceptionCode(int code, String description) {

        this.code = code;
        this.description = description;
    }

}
