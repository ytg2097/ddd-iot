package io.ac.iot.devicemodel.model.qrcodepaserule;

import io.ac.iot.adapter.rest.command.DevicePropertyParseRuleCreateCommand;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static io.ac.iot.devicemodel.model.qrcodepaserule.PropertyType.IDENTIFIER;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Data
@Entity
public class DevicePropertyParseRule {

    @EmbeddedId
    private PropertyParseRulePK primaryKey;

    private Integer startPoint;

    private Integer step;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    private String name;

    public static DevicePropertyParseRule create(String modelId, DevicePropertyParseRuleCreateCommand createCommand){

        DevicePropertyParseRule rule = new DevicePropertyParseRule();
        rule.setPrimaryKey(PropertyParseRulePK.builder().modelId(modelId).ruleKey(createCommand.getKey()).build());
        rule.setPropertyType(PropertyType.valueOf(createCommand.getType()));
        rule.setStartPoint(createCommand.getStartPoint());
        rule.setStep(createCommand.getStep());
        rule.setName(createCommand.getName());
        return rule;
    }

    public boolean isIdentifier(){

        return IDENTIFIER.equals(this.propertyType);
    }
}
