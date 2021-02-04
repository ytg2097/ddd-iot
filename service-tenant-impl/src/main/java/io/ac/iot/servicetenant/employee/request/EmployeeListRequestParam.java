package io.ac.iot.servicetenant.employee.request;

import common.rest.BasePageQueryParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-28
 **/
@Getter
@Setter
public class EmployeeListRequestParam extends BasePageQueryParam {

    private String name;

    private String email;
}
