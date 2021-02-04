package io.ac.iot.servicetenant.employee;

import io.ac.iot.servicetenant.common.domain.BaseEntityRepository;
import io.ac.iot.servicetenant.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
public interface EmployeeRepository extends BaseEntityRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

}
