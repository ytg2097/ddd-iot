package io.ac.iot.servicetenant.employee;

import io.ac.iot.servicetenant.common.util.RequestIdHolder;
import io.ac.iot.servicetenant.employee.command.EmployeeCreateCommand;
import io.ac.iot.servicetenant.employee.command.EmployeeModifyCommand;
import io.ac.iot.servicetenant.employee.model.Employee;
import io.ac.iot.servicetenant.employee.request.EmployeeListRequestParam;
import io.ac.iot.servicetenant.representation.EmployeeRepresentation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;

import static io.ac.iot.servicetenant.common.util.PageUtil.getPageable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * 创建一个员工
     * @param createCommand
     * @return
     */
    public Long create(EmployeeCreateCommand createCommand) {

        Employee employee = Employee.create(createCommand);
        employeeRepository.save(employee);
        return employee.getId();
    }


    /**
     * 返回一个员工
     * @param employeeId
     * @return
     */
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public EmployeeRepresentation findOne(Long employeeId) {

        Employee employee = employeeRepository.findOrElseThrow(employeeId);

        return employee.toRepresentation();
    }

    /**
     * 分页
     * @param requestParam
     * @return
     */
    public Page<Employee> listEmployee(EmployeeListRequestParam requestParam) {

        return employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotEmpty(requestParam.getName())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + requestParam.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(requestParam.getEmail())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("email"), "%" + requestParam.getEmail() + "%"));
            }
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("tenantId"), RequestIdHolder.tid.get()));
            return predicate;
        }, getPageable(requestParam));
    }

    /**
     * 编辑员工
     * @param modifyCommand
     */
    public void modify(EmployeeModifyCommand modifyCommand) {

        Employee employee = employeeRepository.getOne(modifyCommand.getId());
        employee.modify(modifyCommand);
        employeeRepository.save(employee);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Long id) {

        employeeRepository.deleteById(id);
    }
}
