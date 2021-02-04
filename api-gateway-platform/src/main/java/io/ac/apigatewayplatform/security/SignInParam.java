package io.ac.apigatewayplatform.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Getter
@Setter
public class SignInParam {

    private String loginName;

    private String password;
}
