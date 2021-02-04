package io.ac.iot.devicemodel.repositories;

import io.ac.iot.devicemodel.model.qrcodepaserule.DevicePropertyParseRule;
import io.ac.iot.devicemodel.model.qrcodepaserule.PropertyParseRulePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
public interface DevicePropertyParseRuleRepository extends JpaRepository<DevicePropertyParseRule, PropertyParseRulePK> {

    @Query(value = "SELECT * FROM device_property_parse_rule  WHERE model_id = :modelId",nativeQuery = true)
    List<DevicePropertyParseRule> findByModelId(@Param("modelId") String modelId);
}
