package io.ac.iot.servicebackedconfig.adapter.rest.representation;

import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Setter
@Getter
public class SetMealListRepresentation {

    private String id;

    private String name;

    private Long price;

    private Boolean enabled;
    public static SetMealListRepresentation of(SetMeal setMeal) {

        SetMealListRepresentation rep = new SetMealListRepresentation();
        rep.name = setMeal.getName();
        rep.id = setMeal.getId();
        rep.price = setMeal.getPrice();
        rep.enabled = setMeal.getEnabled();
        return rep;
    }
}
