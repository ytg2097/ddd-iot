package io.ac.iot.servicedatahub.aggregation.forward;

import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
public interface ForwardRepository extends JpaRepository<Forward,Long> {
}
