package io.ac.iot.servicedatahub.aggregation.alarm;

import io.ac.iot.servicedatahub.aggregation.alarm.root.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
public interface AlarmRepository extends JpaRepository<Alarm,Long> {
}
