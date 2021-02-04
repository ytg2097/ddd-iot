package io.ac.iot.servicebackedconfig.aggregation.setmeal;

import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
public interface SetMealRepository extends JpaRepository<SetMeal,String>, JpaSpecificationExecutor<SetMeal> {
}
