package io.ac.iot.servicedatahub.aggregation.ruleengine.repositories;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface ActionRuleRepository extends JpaRepository<ActionRule,Integer> {

    List<ActionRule> findByEngineId(String engineId);
}
