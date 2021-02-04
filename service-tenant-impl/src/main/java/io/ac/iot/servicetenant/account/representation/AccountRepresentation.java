package io.ac.iot.servicetenant.account.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-19
 **/
@Getter
@Setter
@ApiModel(value = "账号")
public class AccountRepresentation {
    @ApiModelProperty(name = "ID")
    private String id;
    @ApiModelProperty(name = "角色")
    private String rule;
    @ApiModelProperty(name = "账号")
    private String loginName;
    @ApiModelProperty(name = "最近登陆时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    @ApiModelProperty(name = "最近登陆IP")
    private String lastLoginIp;
    @ApiModelProperty(name = "激活状态")
    private String status;
    @ApiModelProperty(name = "账号级别")
    private String level;
}
