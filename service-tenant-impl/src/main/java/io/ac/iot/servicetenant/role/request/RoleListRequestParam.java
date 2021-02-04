package io.ac.iot.servicetenant.role.request;

import common.rest.BasePageQueryParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Getter
@Setter
public class RoleListRequestParam extends BasePageQueryParam {

    private String name;
}
