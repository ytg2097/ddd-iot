package io.ac.iot.servicedatahub.aggregation.ruleengine;

import common.exception.BusinessException;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleCreatedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleDeletedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleModifiedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleStateChangedEvent;
import io.ac.iot.servicedatahub.aggregation.device.DeviceService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.factory.RuleFactory;
import io.ac.iot.servicedatahub.aggregation.ruleengine.repositories.RuleEngineRepository;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
@Component
@Slf4j
public class RuleEventHandler {

    private final RuleEngineRepository ruleEngineRepository;
    private final RuleFactory ruleFactory;
    private final RuleEngineService ruleEngineService;
    @Autowired
    private DeviceService deviceService;

    public RuleEventHandler(RuleEngineRepository ruleEngineRepository, RuleFactory ruleFactory, RuleEngineService ruleEngineService) {
        this.ruleEngineRepository = ruleEngineRepository;
        this.ruleFactory = ruleFactory;
        this.ruleEngineService = ruleEngineService;
    }

    /**
     * 规则引擎新增事件
     * @param event
     */
    public void on(RuleCreatedEvent event){

        RuleEngine rule = ruleFactory.create(event);
        ruleEngineRepository.save(rule);
    }

    /**
     * 规则引擎删除事件
     * @param event
     */
    public void on(RuleDeletedEvent event){

        ruleEngineRepository.deleteByEngineId(event.getEngineId());
        deviceService.evictCache();
        ruleEngineService.evictAlarmRuleCache(event.getEngineId());
        ruleEngineService.evictActionRuleCache(event.getEngineId());
        ruleEngineService.evictForwardRuleCache(event.getEngineId());
    }

    /**
     * 规则引擎启用状态修改事件
     * @param event
     */
    public void on(RuleStateChangedEvent event){

        RuleEngine engine = ruleEngineRepository.findByEngineId(event.getEngineId());
        engine.changeState(event.getState());
        ruleEngineRepository.save(engine);

        deviceService.evictCache();
    }

    /**
     * 规则引擎修改事件
     * @param event
     */
    public void on(RuleModifiedEvent event) {

        RuleEngine engine = ruleEngineRepository.findByEngineId(event.getEngineId());

        try {
            engine.modify(event);
        } catch (IOException e) {
            log.error("Rule modify error  {}, ruleId  {}",e.getMessage(),event.getEngineId());
            throw new BusinessException(500,e.getMessage());
        }

        ruleEngineRepository.save(engine);

        deviceService.evictCache();
    }
}
