package io.ac.iot.servicetenant.role.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Getter
@Setter
public class RoleRepresentation {

    private String id;

    private String name;

    private String description;

    private Integer subscribers;

    private String roleLevel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastModifyTime;
}
