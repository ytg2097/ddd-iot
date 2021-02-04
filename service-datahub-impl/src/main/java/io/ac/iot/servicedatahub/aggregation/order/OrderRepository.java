package io.ac.iot.servicedatahub.aggregation.order;

import io.ac.iot.servicedatahub.aggregation.order.root.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
public interface OrderRepository extends JpaRepository<Order,Long> {
}
