package io.ac.iot.servicebackedconfig.aggregation.setmeal.factory;

import io.ac.iot.servicebackedconfig.adapter.rest.command.SetMealCreateCommand;
import io.ac.iot.servicebackedconfig.aggregation.menu.root.Menu;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.AccessibleMenu;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.Feature;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-25
 **/
@Component
@RequiredArgsConstructor
public class SetMealFactory {

    public SetMeal get(SetMealCreateCommand createCommand){

        SetMeal setMeal = new SetMeal();

        setMeal.setId(gen32());
        setMeal.setEnabled(false);

        setMeal.setPrice(createCommand.getPrice());
        setMeal.setName(createCommand.getName());

        setMeal.setFeature(Feature.of(createCommand.getFeature()));

        setMeal.setDeviceNumber(createCommand.getDeviceNumber());
        setMeal.setUnitTimeCommandLimiter(createCommand.getUnitTimeCommandLimiter());

        setMeal.setAccessibleMenuList(createCommand.getMenuList().stream().filter(SetMealCreateCommand.Menu::getView).map(menu -> this.accessibleMenu(menu, setMeal.getId())).collect(Collectors.toList()));

        return setMeal;
    }

    private AccessibleMenu accessibleMenu(SetMealCreateCommand.Menu menu, String setMealId) {

        AccessibleMenu accessibleMenu = new AccessibleMenu();
        accessibleMenu.setSetMealId(setMealId);
        accessibleMenu.setDelete(Optional.ofNullable(menu.getDelete()).orElse(false));
        accessibleMenu.setView(Optional.ofNullable(menu.getView()).orElse(false));
        accessibleMenu.setModify(Optional.ofNullable(menu.getModify()).orElse(false));
        accessibleMenu.setCreate(Optional.ofNullable(menu.getCreate()).orElse(false));
        accessibleMenu.setMenu(new Menu(menu.getMenuId()));
        accessibleMenu.setId(gen32());

        if (Objects.nonNull(menu.getChildren())) {
            List<AccessibleMenu> children = menu.getChildren().stream().filter(SetMealCreateCommand.Menu::getView).map(mm -> this.accessibleMenu(mm, setMealId)).collect(Collectors.toList());
            accessibleMenu.setMenus(children);
        }
        return accessibleMenu;
    }
}
