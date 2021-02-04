package io.ac.iot.servicetenant.account.command;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-08-26
 **/
@Data
public class PasswordModifyCommand {

    @NotBlank(message = "账号ID不能为空")
    private String id;

    @NotBlank(message = "旧密码不能为空")
    @Length(min = 6,max = 32,message = "密码长度必须在6到32位之间")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Length(min = 6,max = 32,message = "密码长度必须在6到32位之间")
    private String newPassword;
}
