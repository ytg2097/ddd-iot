package io.ac.iot.servicetenant.request;

import common.rest.BasePageQueryParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Getter
@Setter
public class TenantListRequest extends BasePageQueryParam {

    private String name;

}
