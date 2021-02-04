package io.ac.iot.servicebackedconfig.aggregation.setmeal.model;

import io.ac.iot.servicebackedconfig.adapter.rest.command.SetMealCreateCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Embeddable
@Getter
@Setter
public class Feature {

    /**
     * 员工功能
     */
    private Boolean employee;

    /**
     * 工单功能
     */
    private Boolean ticket;

    /**
     * 定位功能
     */
    private Boolean gps;

    /**
     * 消息通知(短信或邮箱)
     */
    private Boolean msg;

    public static Feature of(SetMealCreateCommand.Feature fe) {

        Feature feature = new Feature();
        feature.setEmployee(fe.getEmployee());
        feature.setGps(fe.getGps());
        feature.setMsg(fe.getMsg());
        feature.setTicket(fe.getMsg());
        return feature;
    }
}
