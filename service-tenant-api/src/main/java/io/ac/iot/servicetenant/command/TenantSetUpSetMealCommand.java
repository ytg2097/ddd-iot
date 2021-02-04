package io.ac.iot.servicetenant.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Setter
@Getter
public class TenantSetUpSetMealCommand {

    private String tenantId;

    private String setMealId;

    private Date date;
}
