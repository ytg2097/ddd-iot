package io.ac.iot.devicegroup;

import io.ac.iot.adapter.rest.command.DeviceGroupCreateCommand;
import io.ac.iot.adapter.rest.representation.common.IdWithNameRepresentation;
import io.ac.iot.devicegroup.model.DeviceGroup;
import io.ac.iot.devicegroup.model.DeviceGroupFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceGroupService {

    private final DeviceGroupFactory deviceGroupFactory;
    private final DeviceGroupRepository groupRepository;

    public DeviceGroupService(DeviceGroupFactory deviceGroupFactory, DeviceGroupRepository groupRepository) {
        this.deviceGroupFactory = deviceGroupFactory;
        this.groupRepository = groupRepository;
    }


    /**
     * 创建一个设备群组
     * @param createCommand
     * @return
     */
    public String create(DeviceGroupCreateCommand createCommand){

        DeviceGroup deviceGroup = deviceGroupFactory.newInstance(createCommand);
        groupRepository.save(deviceGroup);
        return deviceGroup.getId();
    }
    
    /**
     * 移除一个群组
     * @param groupId
     * @return
     */
    public Boolean removeGroup(String groupId) {

        DeviceGroup deviceGroup = findDeviceGroup(groupId);
        groupRepository.delete(deviceGroup);
        return TRUE;
    }

    /**
     * 移动群组
     * @param groupId
     * @param targetGroupId
     */
    public Boolean moveGroup(String groupId, String targetGroupId) {

        DeviceGroup deviceGroup = findDeviceGroup(groupId);
        deviceGroup.move(targetGroupId);
        groupRepository.save(deviceGroup);
        return TRUE;
    }

    /**
     * 绑定设备群组与员工群组
     * @param deviceGroupId
     * @param employeeGroupId
     */
    public Boolean bindEmployeeGroup(String deviceGroupId, String employeeGroupId) {

        DeviceGroup deviceGroup = findDeviceGroup(deviceGroupId);
        deviceGroup.bindEmployeeGroup(employeeGroupId);
        groupRepository.save(deviceGroup);
        return TRUE;
    }

    /**
     * 解绑员工群组下关联的所有设备群组
     * @param employeeGroupId
     */
    public void unbindEmployeeGroup(String employeeGroupId) {

        List<DeviceGroup> deviceGroups = groupRepository.findByEmployeeGroupIdEquals(employeeGroupId);
        deviceGroups.forEach(DeviceGroup::unbindEmployeeGroup);
        groupRepository.saveAll(deviceGroups);
    }

    public List<IdWithNameRepresentation> listByTenantId(String tId) {

        return IdWithNameRepresentation.newInstance(groupRepository.findByTenantIdEquals(tId));
    }

    private DeviceGroup findDeviceGroup(String groupId) {

        return groupRepository.findOrElseThrow((groupId));
    }



}
