package io.ac.iot.servicebackedconfig.adapter.rest.request;

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
public class SetMealListRequest extends BasePageQueryParam {

    private String name;

    private Boolean enable;
}
