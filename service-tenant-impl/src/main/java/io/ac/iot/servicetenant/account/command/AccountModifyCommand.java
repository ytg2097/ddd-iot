package io.ac.iot.servicetenant.account.command;

import io.ac.iot.servicelog.AbstractJsonStringArgs;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-07-01
 **/
@Getter
@Setter
public class AccountModifyCommand extends AbstractJsonStringArgs {

    @NotNull
    private String id;

    @NotBlank
    private String userName;

    @Length(min = 11,max = 11,message = "手机号码非法")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank
    private String loginName;

    private List<String> roleIds;
}
