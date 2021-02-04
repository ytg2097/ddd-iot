package io.ac.iot.servicedatahub.aggregation.ruleengine;

import io.ac.iot.servicedatahub.aggregation.ruleengine.repositories.*;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionRule;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.AlarmRule;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardRule;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.orderrule.OrderRule;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RuleEngineService {

    private final RuleEngineRepository ruleEngineRepository;
    private final AlarmRuleRepository alarmRuleRepository;
    private final ActionRuleRepository actionRuleRepository;
    private final ForwardRuleRepository forwardRuleRepository;
    private final OrderRuleRepository orderRuleRepository;

    /**
     * 查询某个规则引擎的触发告警规则
     * @param engineId
     * @return
     */
    @Cacheable(value = "alarm-rule", key = "#engineId")
    public List<AlarmRule> findAlarmRuleByEngineId(String engineId){

        return alarmRuleRepository.findByEngineId(engineId);
    }

    @CacheEvict(value = "alarm-rule", key = "#engineId")
    public void evictAlarmRuleCache(String engineId){}

    /**
     * 查询某个规则引擎的触发命令规则
     * @param engineId
     * @return
     */
    @Cacheable(value = "action-rule", key = "#engineId")
    public List<ActionRule> findActionRuleByEngineId(String engineId){

        return actionRuleRepository.findByEngineId(engineId);
    }

    @CacheEvict(value = "action-rule", key = "#engineId")
    public void evictActionRuleCache(String engineId){}

    /**
     * 查询某个规则引擎的触发数据转发规则
     * @param engineId
     * @return
     */
    @Cacheable(value = "forward-rule", key = "#engineId")
    public List<ForwardRule> findForwardRuleByEngineId(String engineId){

        return forwardRuleRepository.findByEngineId(engineId);
    }

    @CacheEvict(value = "forward-rule", key = "#engineId")
    public void evictForwardRuleCache(String engineId){}

    /**
     * 查询某个规则引擎的触发数据转发规则
     * @param engineId
     * @return
     */
    @Cacheable(value = "order-rule", key = "#engineId")
    public OrderRule findOrderRuleByEngineId(String engineId){

        return orderRuleRepository.findByEngineId(engineId);
    }

    @CacheEvict(value = "rule-rule", key = "#engineId")
    public void evictOrderRuleCache(String engineId){}

    /**
     * 查询某个设备的可运行规则引擎
     * @param deviceId
     * @return
     */
    public List<RuleEngine> findByDeviceIdExists(String deviceId){

        return ruleEngineRepository.findByEnableIsTrueAndScope_DeviceIdsExists(deviceId);
    }


}
