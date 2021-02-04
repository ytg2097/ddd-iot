package io.ac.iot.device;

import common.rest.PageableRestResult;
import io.ac.iot.adapter.rest.command.DeviceCreateCommand;
import io.ac.iot.adapter.rest.representation.DeviceListRepresentation;
import io.ac.iot.adapter.rest.representation.common.IdWithNameRepresentation;
import io.ac.iot.adapter.rest.request.DeviceListRequest;
import io.ac.iot.common.util.JpaPageable;
import io.ac.iot.common.util.PageUtil;
import io.ac.iot.device.model.Device;
import io.ac.iot.device.model.DeviceFactory;
import io.ac.iot.device.represetation.DeviceRepresentation;
import io.ac.iot.devicemodel.repositories.DeviceModelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.ac.iot.common.util.PageUtil.map;
import static java.lang.Boolean.TRUE;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceFactory deviceFactory;
    private final DeviceModelRepository deviceModelRepository;

    public DeviceService(DeviceRepository deviceRepository, DeviceFactory deviceFactory, DeviceModelRepository deviceModelRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceFactory = deviceFactory;
        this.deviceModelRepository = deviceModelRepository;
    }

    /**
     * 手动创建一个设备
     * @param createCommand
     * @return
     */
    public String create(DeviceCreateCommand createCommand){

        Device device = deviceFactory.createByCommand(createCommand);
        deviceRepository.doSave(device);
        return device.getId();
    }

    public String create(String input,String modelId){

        Device device = deviceFactory.newInstanceByModel(input, deviceModelRepository.findOrElseThrow(modelId));
        deviceRepository.doSave(device);
        return device.getId();
    }

    /**
     * find by id
     * @param id
     * @return
     */
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public DeviceRepresentation findOne(String id){

        Device device = findDevice(id);

        return device.toRepresentation();
    }

    /**
     * 批量绑定设备群组
     * @param groupId
     * @param deviceIds
     */
    public Boolean bindGroup(String groupId, List<String> deviceIds) {

//        deviceRepository.batchUpdateGroupId(groupId,deviceIds);
        List<Device> all = deviceRepository.findAllById(deviceIds);
        for (Device device : all) {
            device.bindGroup(groupId);
        }
        deviceRepository.doSave(all);
        return TRUE;
    }

    /**
     * 解绑设备群组
     * @param deviceIds
     * @return
     */
    public Boolean unbindGroup(List<String> deviceIds) {

        this.bindGroup(null,deviceIds);
        return TRUE;
    }

    private Device findDevice(String id) {

        return deviceRepository.getOne(id);
    }

    /**
     * 移除所有标签
     * @param id
     */
    public void removeTag(Integer id) {

        deviceRepository.removeTag(id);
    }

    public List<IdWithNameRepresentation> listByTenantId(String tId) {

        return IdWithNameRepresentation.newInstance(deviceRepository.findByTenantIdEquals(tId));
    }

    public Page<Device> list(DeviceListRequest request){

        return deviceRepository.findAll((root, query, builder) -> {
            if (StringUtils.isNotBlank(request.getSearchValue())) {
                builder.conjunction();
            }
            return builder.conjunction();
        }, JpaPageable.of(request));
    }
}
