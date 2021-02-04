package io.ac.iot.device.exception;

import common.exception.BusinessException;
import lombok.Getter;

/**
 * @description:  20000-29999 租户相关服务异常码
 *                          -- service-tenant        20000 - 20999
 * @description:  30000-39999 设备相关服务异常码
 *                          -- service-device        30000 - 30999
 *                             -- 30000 - 30099  设备异常
 *                             -- 30100 - 30199  设备群组异常
 *                             -- 30200 - 30299  设备型号异常
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
public enum DeviceExceptionCode implements BusinessException.Exception {

    DEVICE_NOT_FOUNT(30000, "设备不存在"),

    DEVICE_GROUP_NOT_FOUNT(30100, "群组不存在"),

    DEVICE_PROPERTY_PARSE_RULE_ALREADY_EXISTED(30200, "设备属性解析规则已存在"),
    IDENTIFIER_PARSE_RULE_ALREADY_EXISTED(30201, "设备识别码解析规则已存在"),
    PROPERTY_PARSE_FAIL(30202, "设备属性解析错误"),
    DEVICE_PROPERTY_PARSE_RULE_NOT_CONFIGURED(30203, "设备属性解析规则未配置"),
    IDENTIFIER_PARSE_RULE_NOT_CONFIGURED(30204, "设备标识码属性解析规则未配置"),
    THERE_ARE_DEVICES_BOUND_TO_THIS_MODEL(30205, "还有绑定到该模型的设备"),
    SERVICE_CAPABILITY_IS_DEFINED(30206, "设备服务能力已定义"),
    DEVICE_TAG_ALREADY_EXISTED(30207, "设备标签已存在"),
    DEVICE_MODEL_ALREADY_EXISTED(30208, "设备型号已存在"),
    ;



    private final String description;

    private final int code;



    DeviceExceptionCode(int code, String description) {

        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }}
