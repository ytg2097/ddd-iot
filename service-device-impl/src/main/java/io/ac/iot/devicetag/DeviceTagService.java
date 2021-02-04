package io.ac.iot.devicetag;

import common.exception.BusinessException;
import common.rest.PageableRestResult;
import io.ac.iot.adapter.rest.command.DeviceTagCreateCommand;
import io.ac.iot.adapter.rest.request.DeviceTagListRequest;
import io.ac.iot.common.util.JpaPageable;
import io.ac.iot.device.DeviceService;
import io.ac.iot.devicetag.model.DeviceTag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;

import static io.ac.iot.device.exception.DeviceExceptionCode.DEVICE_TAG_ALREADY_EXISTED;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceTagService {

    private final DeviceTagRepository deviceTagRepository;

    private final DeviceService deviceService;

    public DeviceTagService(DeviceTagRepository deviceTagRepository, DeviceService deviceService) {
        this.deviceTagRepository = deviceTagRepository;
        this.deviceService = deviceService;
    }

    /**
     * 新增
     * @param createCommand
     */
    public void create(DeviceTagCreateCommand createCommand){

        if (deviceTagRepository.existsByNameEqualsOrValueEquals(createCommand.getName(),createCommand.getValue())){
            throw new BusinessException(DEVICE_TAG_ALREADY_EXISTED);
        }

        DeviceTag tag = DeviceTag.create(createCommand);

        deviceTagRepository.save(tag);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id){

        deviceService.removeTag(id);

        deviceTagRepository.deleteById(id);
    }

    /**
     * 分页
     * @param request
     * @return
     */
    public Page<DeviceTag> list(DeviceTagListRequest request){

        return deviceTagRepository.findAll((root,query,builder) -> {

            Predicate conjunction = builder.conjunction();
            if (StringUtils.isNotEmpty(request.getName())){
                conjunction.getExpressions().add(builder.equal(root.get("name"),request.getName()));
            }
            if (StringUtils.isNotEmpty(request.getValue())){
                conjunction.getExpressions().add(builder.equal(root.get("value"),request.getValue()));
            }

            return conjunction;
        }, JpaPageable.of(request));
    }
}
