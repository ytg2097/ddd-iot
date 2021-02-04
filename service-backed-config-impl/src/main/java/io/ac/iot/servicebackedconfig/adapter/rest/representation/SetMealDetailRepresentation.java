package io.ac.iot.servicebackedconfig.adapter.rest.representation;

import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Getter
@Setter
public class SetMealDetailRepresentation {

    private Long price;

    private Long deviceNumber;

    private Integer accountNumber;

    private Integer unitTimeCommandLimiter;

    private Boolean employee;

    private Boolean ticket;

    private Boolean msg;

    private Boolean gps;

    private String name;

    private String id;
    public static SetMealDetailRepresentation of(SetMeal setMeal) {

        SetMealDetailRepresentation rep = new SetMealDetailRepresentation();

        rep.deviceNumber = setMeal.getDeviceNumber();
        rep.accountNumber = setMeal.getAccountNumber();
        rep.unitTimeCommandLimiter = setMeal.getUnitTimeCommandLimiter();
        rep.price = setMeal.getPrice();
        rep.name = setMeal.getName();
        rep.id = setMeal.getId();
        rep.employee = setMeal.getFeature().getEmployee();
        rep.gps = setMeal.getFeature().getGps();
        rep.msg = setMeal.getFeature().getMsg();
        rep.ticket = setMeal.getFeature().getTicket();

        return rep;
    }
}
