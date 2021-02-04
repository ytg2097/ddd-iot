package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
@Setter
public class UserCreateCommand {

    private String userName;

    private String phone;

    private String email;

}
