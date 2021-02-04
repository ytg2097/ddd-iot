package io.ac.iot.servicetenant.role.repository;

import io.ac.iot.servicetenant.role.model.AccessibleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
public interface AccessibleMenuRepository extends JpaRepository<AccessibleMenu,String> {

    List<AccessibleMenu> findByMenuId(String menu);

    void deleteByMenuIdIn(List<String> menuIds);

    List<AccessibleMenu> findByRole_IdIn(List<String> roleIds);

    void deleteAccessibleMenuByRole_Id(String roleId);
}
