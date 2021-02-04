package io.ac.iot.servicetenant.tenant;


import io.ac.iot.servicetenant.common.domain.BaseEntityRepository;
import io.ac.iot.servicetenant.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
public interface TenantRepository extends JpaRepository<Tenant, String>, BaseEntityRepository<Tenant,String>, JpaSpecificationExecutor<Tenant> {

    /**
     * 获取租户, 忽略valid条件
     * @param id
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM tenant WHERE id = ?1")
    Tenant findByIdIgnoreValid(String id);
}
