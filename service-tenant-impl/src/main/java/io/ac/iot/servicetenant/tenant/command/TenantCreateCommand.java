package io.ac.iot.servicetenant.tenant.command;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Getter
@Setter
public class TenantCreateCommand {

    @NotBlank(message = "租户名称不能为空")
    @Length(max = 80,message = "租户名称长度不得超过80个字符")
    private String name;

    @Valid
    private Address address;

    @NotBlank
    private String fixedPhone;
    @NotBlank
    private String email;

    private @Valid Account account;

    @Getter
    @Setter
    public class Address{
        @NotBlank
        private String province;
        @NotBlank
        private String city;

        private String detail;
    }

    @Getter
    @Setter
    public class Account{

        @NotBlank(message = "登录名不能为空")
        @Length(min = 6,max = 32,message = "登录名长度必须在6到32位之间")
        private String loginName;

        @NotBlank(message = "联系人姓名不能为空")
        private String userName;

        @Length(min = 11,max = 11,message = "手机号码非法")
        private String phone;

        @Email(message = "邮箱格式不正确")
        private String email;

    }
}

