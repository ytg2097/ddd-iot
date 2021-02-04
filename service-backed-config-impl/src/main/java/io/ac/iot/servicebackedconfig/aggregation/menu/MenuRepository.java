package io.ac.iot.servicebackedconfig.aggregation.menu;

import io.ac.iot.servicebackedconfig.aggregation.menu.root.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
public interface MenuRepository extends JpaRepository<Menu,String> {

    List<Menu> findByRootIsTrueOrderBySort();

}
