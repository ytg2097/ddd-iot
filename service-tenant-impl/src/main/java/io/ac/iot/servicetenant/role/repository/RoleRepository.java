package io.ac.iot.servicetenant.role.repository;

import io.ac.iot.servicetenant.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
public interface RoleRepository extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {

    Boolean existsByNameEquals(String name);

}
