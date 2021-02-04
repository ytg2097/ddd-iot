package io.ac.iot.devicemodel.repositories;

import io.ac.iot.common.domain.BaseEntityRepository;
import io.ac.iot.devicemodel.model.DeviceModel;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
public interface DeviceModelRepository extends BaseEntityRepository<DeviceModel,String> {

    boolean existsByManufacturerIdAndModel(String manufacturerId,String model);

    List<DeviceModel> findByTenantIdEquals(String tenantId);
}
