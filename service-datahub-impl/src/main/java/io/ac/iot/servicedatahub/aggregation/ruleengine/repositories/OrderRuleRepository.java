package io.ac.iot.servicedatahub.aggregation.ruleengine.repositories;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.orderrule.OrderRule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
public interface OrderRuleRepository extends JpaRepository<OrderRule,Integer> {
    OrderRule findByEngineId(String engineId);
}
