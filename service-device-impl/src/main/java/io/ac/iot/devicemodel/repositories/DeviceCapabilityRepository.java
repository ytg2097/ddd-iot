package io.ac.iot.devicemodel.repositories;

import io.ac.iot.devicemodel.model.capability.DeviceCapability;
import io.ac.starter.DomainEventAwareRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
public interface DeviceCapabilityRepository extends DomainEventAwareRepository<DeviceCapability,Integer> {

    boolean existsByNameAndModelId(String name,String modelId);
}
