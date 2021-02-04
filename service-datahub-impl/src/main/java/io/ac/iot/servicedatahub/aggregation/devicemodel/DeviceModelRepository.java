package io.ac.iot.servicedatahub.aggregation.devicemodel;

import io.ac.iot.servicedatahub.aggregation.devicemodel.root.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
public interface DeviceModelRepository extends JpaRepository<DeviceModel,String> {
}
