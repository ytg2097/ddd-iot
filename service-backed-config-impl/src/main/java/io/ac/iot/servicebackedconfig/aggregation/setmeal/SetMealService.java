package io.ac.iot.servicebackedconfig.aggregation.setmeal;

import io.ac.iot.servicebackedconfig.adapter.rest.command.SetMealCreateCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.SetMealDetailRepresentation;
import io.ac.iot.servicebackedconfig.adapter.rest.request.SetMealListRequest;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.factory.SetMealFactory;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.AccessibleMenu;
import io.ac.iot.servicebackedconfig.common.util.JpaPageable;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SetMealService {

    private final SetMealRepository setMealRepository;
    private final SetMealFactory setMealFactory;


    /**
     * 新增
     * @param createCommand
     */
    public void create(SetMealCreateCommand createCommand) {

        SetMeal setMeal = setMealFactory.get(createCommand);

        setMealRepository.save(setMeal);
    }

    /**
     * 切换套餐开放状态
     * @param id
     */
    public void toggleEnableStatus(String id){

        SetMeal setMeal = setMealRepository.findById(id).get();
        setMeal.toggleEnableStatus();
        setMealRepository.save(setMeal);
    }

    /**
     * 已启用的套餐列表
     * @return
     */
    public List<SetMealDetailRepresentation> enabledSetMeal() {

        return setMealRepository.findAll().stream()
                .map(SetMealDetailRepresentation::of)
                .collect(toList());
    }

    /**
     * 分页
     * @param request
     * @return
     */
    public Page<SetMeal> listSetMeal(SetMealListRequest request) {

        return setMealRepository.findAll((root,query,builder) -> {

            Predicate predicate = builder.conjunction();
            if (StringUtils.isNotEmpty(request.getName())){
                predicate.getExpressions().add(builder.equal(root.get("name"),request.getName()));
            }
            if (Objects.nonNull(request.getEnable())){
                predicate.getExpressions().add(builder.equal(root.get("enabled"),request.getEnable()));
            }
            return predicate;
        }, JpaPageable.of(request));
    }

    public SetMeal getOne(String id) {

        return setMealRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<MenuDTO> findVisibleMenuListById(String id) {

        return setMealRepository.getOne(id)
                .menuList();
    }
}
