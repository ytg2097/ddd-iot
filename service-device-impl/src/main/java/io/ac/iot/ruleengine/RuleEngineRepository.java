package io.ac.iot.ruleengine;

import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.starter.DomainEventAwareRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
public interface RuleEngineRepository extends DomainEventAwareRepository<RuleEngine,String> {
}
