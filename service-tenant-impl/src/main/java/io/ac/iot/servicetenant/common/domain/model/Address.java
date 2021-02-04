package io.ac.iot.servicetenant.common.domain.model;

import common.util.ObjectUtil;
import io.ac.iot.servicetenant.tenant.command.TenantCreateCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
@Getter
@Setter
@Embeddable
public class Address {

    private String province;

    private String city;

    private String detail;

    public static Address of(TenantCreateCommand.Address address){

        return ObjectUtil.copyField(address,new Address());
    }
}
