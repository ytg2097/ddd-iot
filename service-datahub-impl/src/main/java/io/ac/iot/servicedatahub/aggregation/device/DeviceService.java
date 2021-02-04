package io.ac.iot.servicedatahub.aggregation.device;

import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import io.ac.iot.servicedatahub.aggregation.device.vo.DeviceWithRuleEngineVO;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final RuleEngineService ruleEngineService;

    /**
     * 根据设备id 查询设备基本属性与正在运行的规则引擎
     * 同时缓存到内存中
     * @param deviceId
     * @return
     */
    @Cacheable(value = "device-with-engine", key = "#deviceId")
    public DeviceWithRuleEngineVO findById(String deviceId){

        Device device = deviceRepository.findByDeviceId(deviceId);
        List<RuleEngine> engines = ruleEngineService.findByDeviceIdExists(deviceId);

        return DeviceWithRuleEngineVO.of(device,engines);
    }


    @CacheEvict(value = "device-engine", key = "#deviceId")
    public void evictCache(String deviceId){}

    @CacheEvict(value = "device-engine",allEntries = true)
    public void evictCache(){}
}
