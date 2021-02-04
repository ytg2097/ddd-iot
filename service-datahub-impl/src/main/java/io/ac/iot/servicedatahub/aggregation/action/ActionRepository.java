package io.ac.iot.servicedatahub.aggregation.action;

import io.ac.iot.servicedatahub.aggregation.action.root.Action;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface ActionRepository extends JpaRepository<Action,Long> {
}
