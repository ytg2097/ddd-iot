package io.ac.iot.servicetenant.tenant.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Data
@Entity
public class SetMeal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date endTime;

    private String setMealId;

    private Integer deviceNumber;

    private Integer accountNumber;

    public static SetMeal of(String setMealId, Date date) {

        SetMeal setMeal = new SetMeal();

        setMeal.setMealId = setMealId;
        setMeal.endTime = date;
        setMeal.deviceNumber = 0;
        setMeal.accountNumber = 0;

        return setMeal;
    }

    public void renew(Date date) {

        this.endTime = date;
    }
}
