package io.ac.iot.ruleengine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Embeddable
@Getter
@Setter
public class TimeRange {

    private Boolean mon;

    private Boolean tue;

    private Boolean wed;

    private Boolean thu;

    private Boolean fri;

    private Boolean sat;

    private Boolean sun;

    private String beginTime;

    private String endTime;
}
