package io.ac.iot.servicedatahub.aggregation.ruleengine.repositories;

import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface RuleEngineRepository extends JpaRepository<RuleEngine,Integer> {

    /**
     * 查询某个设备的可运行规则
     * @param deviceId
     * @return
     */
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM rule_engine_device_ids d LEFT JOIN rule_engine r ON r.id = d.rule_engine_id WHERE device_ids = ?1 AND r.enable = true "
    )
    List<RuleEngine> findByEnableIsTrueAndScope_DeviceIdsExists(String deviceId);

    void  deleteByEngineId(String engineId);

    RuleEngine findByEngineId(String engineId);
}
