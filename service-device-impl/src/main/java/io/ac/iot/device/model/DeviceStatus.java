package io.ac.iot.device.model;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
public enum DeviceStatus {

    NORMAL("正常"),
    ABNORMAL("异常"),
    OFFLINE("离线");

    DeviceStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
