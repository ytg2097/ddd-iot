package io.ac.iot.servicetenant.role.model;

import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import io.ac.iot.servicetenant.role.command.RoleCreateCommand;
import io.ac.starter.util.RequestHeadHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Component
public class RoleFactory {

    public Role createGlobalManager(String tenantId, List<MenuDTO> menus) {

        Role role = new Role();
        role.setName("Global Manager");
        role.setRoleLevel(RoleLevel.ROOT);
        role.setId(gen32());
        role.setSubscribers(0);
        role.setAccessibleMenuList(menus.stream().map(menu -> {
            AccessibleMenu accessibleMenu = new AccessibleMenu();
            accessibleMenu.setMenuId(menu.getMenuId());
            accessibleMenu.setCreate(true);
            accessibleMenu.setModify(true);
            accessibleMenu.setView(true);
            accessibleMenu.setDelete(true);
            accessibleMenu.setRole(role);
            accessibleMenu.setId(gen32());
            return accessibleMenu;
        }).collect(Collectors.toList()));
        role.setTenantId(tenantId);
        return role;
    }

    public Role createGlobalReader(String tenantId, List<MenuDTO> menus) {

        Role role = new Role();
        role.setName("Global Reader");
        role.setRoleLevel(RoleLevel.ROOT);
        role.setId(gen32());
        role.setSubscribers(0);
        role.setTenantId(tenantId);
        role.setAccessibleMenuList(menus.stream().map(menu -> {
            AccessibleMenu accessibleMenu = new AccessibleMenu();
            accessibleMenu.setMenuId(menu.getMenuId());
            accessibleMenu.setCreate(false);
            accessibleMenu.setModify(false);
            accessibleMenu.setView(true);
            accessibleMenu.setDelete(false);
            accessibleMenu.setId(gen32());
            return accessibleMenu;
        }).collect(Collectors.toList()));
        return role;
    }

    public Role create(RoleCreateCommand createCommand) {

        Role role = new Role();
        role.setName(createCommand.getName());
        role.setDescription(createCommand.getDescription());
        role.setRoleLevel(RoleLevel.ORDINARY);
        role.setId(gen32());
        role.setSubscribers(0);
        role.setAccessibleMenuList(createCommand.getMenuList().stream().filter(RoleCreateCommand.Menu::getView).map(menu -> this.accessibleMenu(menu, role)).collect(Collectors.toList()));
        role.setTenantId(RequestHeadHolder.find(RequestHeadHolder.RequestHead.TENANT_ID));

        return role;
    }

    private AccessibleMenu accessibleMenu(RoleCreateCommand.Menu menu, Role role) {

        AccessibleMenu accessibleMenu = new AccessibleMenu();
        accessibleMenu.setMenuId(menu.getMenuId());
        accessibleMenu.setDelete(Optional.ofNullable(menu.getDelete()).orElse(false));
        accessibleMenu.setView(Optional.ofNullable(menu.getView()).orElse(false));
        accessibleMenu.setModify(Optional.ofNullable(menu.getModify()).orElse(false));
        accessibleMenu.setCreate(Optional.ofNullable(menu.getCreate()).orElse(false));
        accessibleMenu.setRole(role);
        accessibleMenu.setId(gen32());

        if (Objects.nonNull(menu.getChildren())) {
            List<AccessibleMenu> children = menu.getChildren().stream().filter(RoleCreateCommand.Menu::getView).map(mm -> this.accessibleMenu(mm, role)).collect(Collectors.toList());
            accessibleMenu.setChildren(children);
        }
        return accessibleMenu;
    }
}
