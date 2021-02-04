package io.ac.iot.devicemodel.model.qrcodepaserule;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Embeddable
@Builder
@Getter
public class PropertyParseRulePK implements Serializable {

    private String modelId;

    private String ruleKey;

}
