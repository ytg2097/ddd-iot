package io.ac.iot.servicetenant.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeRepresentation {

    private String name;

    private String phone;

    private Long id;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
