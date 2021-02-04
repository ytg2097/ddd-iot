package io.ac.iot.servicedatahub.core.bean;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import lombok.Getter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Getter
public class MessageContext {

    private String deviceId;

    private JsonNode data;

    private RuleEngine engine;

    public MessageContext(RuleEngine engine,JsonNode data){

        this.engine = engine;
        this.data = data;
        //todo
        this.deviceId = data.get("deviceId").toString();
    }
}
