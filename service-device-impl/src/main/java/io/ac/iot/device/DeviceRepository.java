package io.ac.iot.device;


import io.ac.iot.common.domain.BaseEntityRepository;
import io.ac.iot.device.model.Device;
import io.ac.starter.DomainEventAwareRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
public interface DeviceRepository extends DomainEventAwareRepository<Device, String> , JpaSpecificationExecutor<Device> {

    @Query(value = "UPDATE Device SET deviceGroupId = :groupId WHERE id IN (:deviceIds)")
    @Modifying
    void batchUpdateGroupId(@Param("groupId") String groupId,@Param("deviceIds") List<String> deviceIds);

    @Query(value = "UPDATE Device SET deviceGroupId = null WHERE id IN (:deviceIds)")
    @Modifying
    void batchRemoveGroupId(@Param("deviceIds") List<String> deviceIds);

    Long countByModelIdEquals(String modelId);

    @Query(nativeQuery = true,
        value = "DELETE device_with_tag WHERE tag_id = ?1 ")
    @Modifying
    void removeTag(Integer id);

    List<Device> findByTenantIdEquals(String tId);
}
