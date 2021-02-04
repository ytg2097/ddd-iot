package io.ac.iot.servicetenant.employeegroup;

import io.ac.iot.servicetenant.common.domain.BaseEntityRepository;
import io.ac.iot.servicetenant.employeegroup.model.EmployeeGroup;
import io.ac.starter.DomainEventAwareRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-31
 **/
public interface EmployeeGroupRepository extends BaseEntityRepository<EmployeeGroup,String>, DomainEventAwareRepository<EmployeeGroup,String> {
}
