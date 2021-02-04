package io.ac.iot.servicetenant.employee.command;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Getter
@Setter
public class EmployeeCreateCommand {

    @NotBlank(message = "员工姓名不能空")
    @Length(max = 15,message = "姓名长度不得超过15个字符")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Length(max = 11,min = 11, message = "手机号位数不合法")
    private String phone;

    @NotBlank
    private String email;
}
