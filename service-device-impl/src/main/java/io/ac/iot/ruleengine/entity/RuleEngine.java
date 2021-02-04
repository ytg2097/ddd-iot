package io.ac.iot.ruleengine.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.ac.iot.ruleengine.entity.actionrule.ActionRule;
import io.ac.iot.ruleengine.entity.alarmrule.AlarmRule;
import io.ac.iot.ruleengine.entity.forwardrule.ForwardRule;
import io.ac.iot.ruleengine.entity.expression.Expression;
import io.ac.iot.ruleengine.event.RuleEngineCreatedEvent;
import io.ac.iot.ruleengine.event.RuleEngineModifiedEvent;
import io.ac.iot.ruleengine.event.RuleEngineStateChangedEvent;
import io.ac.iot.ruleengine.model.Scope;
import io.ac.iot.ruleengine.model.TimeRange;
import io.ac.starter.DomainEventAwareAggregate;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Entity
@Data
@TypeDef(name = "json",typeClass = JsonStringType.class)
@Builder
public class RuleEngine extends DomainEventAwareAggregate {

    @Id
    private String id;

    private String name;

    private Boolean enable;

    @Embedded
    private TimeRange timeRange;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "expression_id",
        foreignKey = @ForeignKey(name = "NONE",value = ConstraintMode.NO_CONSTRAINT)
    )
    private Expression expression;

    /**
     * 本条规则作用域
     */
    @Embedded
    private Scope scope;

    /**
     * 触发告警通知
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "rule_id",
            foreignKey = @ForeignKey(name = "NONE",value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<AlarmRule> alarmRuleList;

    /**
     * 触发动作
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "rule_id",
            foreignKey = @ForeignKey(name = "NONE",value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<ActionRule> actionRuleList;

    /**
     * 数据转发
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "rule_id",
            foreignKey = @ForeignKey(name = "NONE",value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<ForwardRule> forwardRuleList;

    public void postConstruct(){

        raiseEvent(new RuleEngineCreatedEvent(this));
    }

    public void postModified(){

        raiseEvent(new RuleEngineModifiedEvent(this));
    }

    public void changeEnableState() {

        this.enable = !this.enable;
        raiseEvent(new RuleEngineStateChangedEvent(this));
    }
}
