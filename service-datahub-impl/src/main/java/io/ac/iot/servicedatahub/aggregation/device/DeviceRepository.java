package io.ac.iot.servicedatahub.aggregation.device;

import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
public interface DeviceRepository extends JpaRepository<Device,String> {

    Device findByDeviceId(String deviceId);
    List<Device> findDistinctByDeviceIdInOrGroupIdIn(List<String> deviceIds,List<String> groupId);
}
