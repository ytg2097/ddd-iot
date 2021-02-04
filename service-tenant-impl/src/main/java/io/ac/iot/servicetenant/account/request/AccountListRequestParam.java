package io.ac.iot.servicetenant.account.request;

import common.rest.BasePageQueryParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-19
 **/
@Getter
@Setter
public class AccountListRequestParam extends BasePageQueryParam {

    private String loginName;
}
