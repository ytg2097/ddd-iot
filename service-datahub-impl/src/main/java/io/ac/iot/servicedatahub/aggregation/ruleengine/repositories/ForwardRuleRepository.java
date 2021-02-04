package io.ac.iot.servicedatahub.aggregation.ruleengine.repositories;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface ForwardRuleRepository extends JpaRepository<ForwardRule,Integer> {

    List<ForwardRule> findByEngineId(String engineId);
}
