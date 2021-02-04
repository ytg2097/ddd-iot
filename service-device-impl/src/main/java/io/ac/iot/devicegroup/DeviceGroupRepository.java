package io.ac.iot.devicegroup;

import io.ac.iot.common.domain.BaseEntityRepository;
import io.ac.iot.devicegroup.model.DeviceGroup;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-31
 **/
public interface DeviceGroupRepository extends BaseEntityRepository<DeviceGroup,String> {

    List<DeviceGroup> findByEmployeeGroupIdEquals(String employeeGroupId);

    List<DeviceGroup> findByTenantIdEquals(String tId);
}
