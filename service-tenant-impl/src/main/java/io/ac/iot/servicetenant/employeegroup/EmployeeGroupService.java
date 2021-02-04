package io.ac.iot.servicetenant.employeegroup;

import io.ac.iot.servicetenant.employeegroup.model.EmployeeGroup;
import io.ac.iot.servicetenant.employeegroup.model.EmployeeGroupFactory;
import io.ac.iot.servicetenant.employeegroup.command.EmployeeGroupCreateCommand;
import io.ac.iot.servicetenant.employee.EmployeeRepository;
import io.ac.iot.servicetenant.employee.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.TRUE;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeGroupService {

    private final EmployeeGroupFactory employeeGroupFactory;
    private final EmployeeGroupRepository groupRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeGroupService(EmployeeGroupFactory employeeGroupFactory, EmployeeGroupRepository groupRepository, EmployeeRepository employeeRepository) {
        this.employeeGroupFactory = employeeGroupFactory;
        this.groupRepository = groupRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * 创建一个员工群组
     * @param createCommand
     * @return
     */
    public String create(EmployeeGroupCreateCommand createCommand){

        EmployeeGroup employeeGroup = employeeGroupFactory.newInstance(createCommand);
        groupRepository.save(employeeGroup);
        return employeeGroup.getId();
    }

    /**
     * 将员工添加到员工群组
     * @param groupId
     * @return
     */
    public Boolean addEmployeeToGroup(String groupId,Long employeeId){

        EmployeeGroup employeeGroup = findEmployeeGroup(groupId);
        Boolean result = employeeGroup.addMember(findEmployee(employeeId));
        groupRepository.save(employeeGroup);
        return result;
    }

    /**
     * 从员工群组中移除员工
     * @param groupId
     * @param employeeId
     * @return
     */
    public Boolean removeEmployeeFromGroup(String groupId, Long employeeId) {

        EmployeeGroup employeeGroup = findEmployeeGroup(groupId);
        Boolean result = employeeGroup.removeMember(findEmployee(employeeId));
        groupRepository.save(employeeGroup);
        return result;
    }


    /**
     * 移动员工群组
     * @param groupId
     * @param targetGroupId
     */
    public Boolean moveGroup(String groupId, String targetGroupId) {

        EmployeeGroup employeeGroup = findEmployeeGroup((groupId));
        employeeGroup.move(targetGroupId);
        groupRepository.save(employeeGroup);
        return TRUE;
    }

    /**
     * 移除一个员工群组
     * @param groupId
     * @return
     */
    public Boolean removeGroup(String groupId) {

        EmployeeGroup employeeGroup = findEmployeeGroup(groupId);
        employeeGroup.destroy();
        groupRepository.doSave(employeeGroup);
        return TRUE;
    }

    private EmployeeGroup findEmployeeGroup(String groupId) {

        return groupRepository.findOrElseThrow(groupId);
    }

    private Employee findEmployee(Long employeeId) {

        return employeeRepository.findOrElseThrow(employeeId);
    }
}
