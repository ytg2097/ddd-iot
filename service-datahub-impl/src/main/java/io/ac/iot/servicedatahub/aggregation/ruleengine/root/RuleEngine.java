package io.ac.iot.servicedatahub.aggregation.ruleengine.root;

import common.JsonUtil;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleModifiedEvent;
import io.ac.iot.servicedatahub.aggregation.ruleengine.model.Scope;
import io.ac.iot.servicedatahub.aggregation.ruleengine.model.TimeRange;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.expression.Expression;
import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.IOException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Data
@Entity
@Builder
public class RuleEngine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String engineId;

    private Boolean enable;

    @Embedded
    private TimeRange timeRange;

    @Embedded
    private Scope scope;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(
            name = "expression_id",
            foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
    )
    private Expression expression;

    public void changeState(Boolean state) {

        this.enable = state;
    }

    public void modify(RuleModifiedEvent event) throws IOException {

        this.scope.setDeviceIds(event.getDeviceIds());
        this.scope.setDeviceGroupId(event.getDeviceGroupId());
        this.scope.setDeviceModelId(event.getDeviceModelId());
        this.timeRange.setEndTime(event.getEndTime());
        this.timeRange.setStartTime(event.getStartTime());
        this.timeRange.setFri(event.getFri());
        this.timeRange.setMon(event.getMon());
        this.timeRange.setSat(event.getSat());
        this.timeRange.setSun(event.getSun());
        this.timeRange.setThu(event.getThu());
        this.timeRange.setTue(event.getTue());
        this.timeRange.setWed(event.getWed());
        this.expression = JsonUtil.fromJson(event.getExpression(),Expression.class);

    }
}
