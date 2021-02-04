package io.ac.iot.ruleengine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Embeddable
@Getter
@Setter
public class Scope {

    private String deviceModelId;

    private String deviceGroupId;

    @ElementCollection
    private List<String> deviceIds;
}
