package io.ac.iot.servicetenant.account.command;

import io.ac.iot.servicelog.AbstractJsonStringArgs;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Getter
@Setter
public class AccountCreateCommand extends AbstractJsonStringArgs {

    @NotBlank(message = "租户Id 不允许为空")
    private String tenantId;

    @NotBlank(message = "登录名不能为空")
    @Length(min = 6,max = 32,message = "登录名长度必须在6到32位之间")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 32,message = "密码长度必须在6到32位之间")
    private String password;

    @NotBlank(message = "账号类型不能为空")
    private String accountType;

    @NotBlank(message = "联系人姓名不能为空")
    private String userName;

    @Length(min = 11,max = 11,message = "手机号码非法")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private List<String> roleIds;


}

