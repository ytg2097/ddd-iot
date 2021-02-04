package io.ac.iot.servicetenant.employee.command;

import io.ac.iot.servicelog.AbstractJsonStringArgs;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-07-01
 **/
@Getter
@Setter
public class EmployeeModifyCommand extends AbstractJsonStringArgs {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @Length(min = 11,max = 11,message = "手机号码非法")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;
}
