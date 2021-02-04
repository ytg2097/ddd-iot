package io.ac.iot.servicedatahub.aggregation.ruleengine.repositories;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.AlarmRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface AlarmRuleRepository extends JpaRepository<AlarmRule,Integer> {

    List<AlarmRule> findByEngineId(String engineId);
}
