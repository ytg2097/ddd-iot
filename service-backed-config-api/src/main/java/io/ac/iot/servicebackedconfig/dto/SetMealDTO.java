package io.ac.iot.servicebackedconfig.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Getter
@Setter
@ToString
public class SetMealDTO {

    private String name;

    private Long deviceNumber;

    private Integer accountNumber;
}
