package io.ac.iot.devicetag;

import io.ac.iot.devicetag.model.DeviceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
public interface DeviceTagRepository extends JpaRepository<DeviceTag,Integer>, JpaSpecificationExecutor<DeviceTag> {

    boolean existsByNameEqualsOrValueEquals(String name,String value);
}
