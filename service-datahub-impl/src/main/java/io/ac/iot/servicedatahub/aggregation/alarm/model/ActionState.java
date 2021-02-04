package io.ac.iot.servicedatahub.aggregation.alarm.model;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public enum ActionState {

    CREATED("已创建"),
    SENT("已发送"),
    ARRIVED("")
    ;

    private String state;

    ActionState(String state) {
        this.state = state;
    }}
