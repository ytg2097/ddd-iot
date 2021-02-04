package io.ac.iot.servicetenant.role;

import common.exception.BusinessException;
import io.ac.iot.servicebackedconfig.client.SetMealClient;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import io.ac.iot.servicetenant.account.AccountService;
import io.ac.iot.servicetenant.account.model.AccountLevel;
import io.ac.iot.servicetenant.account.representation.AccountDetailRepresentation;
import io.ac.iot.servicetenant.role.command.RoleCreateCommand;
import io.ac.iot.servicetenant.role.command.RoleModifyCommand;
import io.ac.iot.servicetenant.role.model.AccessibleMenu;
import io.ac.iot.servicetenant.role.model.Role;
import io.ac.iot.servicetenant.role.model.RoleFactory;
import io.ac.iot.servicetenant.role.repository.AccessibleMenuRepository;
import io.ac.iot.servicetenant.role.repository.RoleRepository;
import io.ac.iot.servicetenant.role.representation.AccessibleMenuRepresentation;
import io.ac.iot.servicetenant.role.representation.RoleDetailRepresentation;
import io.ac.iot.servicetenant.role.representation.RoleSimpleRepresentation;
import io.ac.iot.servicetenant.role.representation.RouterRepresentation;
import io.ac.iot.servicetenant.role.request.RoleListRequestParam;
import io.ac.iot.servicetenant.tenant.TenantService;
import io.ac.iot.servicetenant.tenant.model.SetMeal;
import io.ac.starter.util.RequestHeadHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.collect.Maps.newHashMap;
import static common.util.UUIDGenerator.gen32;
import static io.ac.iot.servicetenant.common.util.PageUtil.getPageable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleFactory roleFactory;
    private final AccessibleMenuRepository accessibleMenuRepository;
    private final SetMealClient setMealClient;
    private final TenantService tenantService;
    private final AccountService accountService;


    /**
     * 所有角色
     *
     * @return
     */
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    /**
     * 创建角色
     *
     * @param createCommand
     * @return
     */
    public String create(RoleCreateCommand createCommand) {

        Role role = roleFactory.create(createCommand);
        roleRepository.save(role);
        return role.getId();
    }

//    /**
//     * 创建基础角色
//     * @param tenantId
//     */
//    public void createRoot(String tenantId) {
//
//        List<MenuDTO> menus = getMenuDTOS(tenantId);
//        Role globalManager = roleFactory.createGlobalManager(tenantId, menus);
//        Role globalReader = roleFactory.createGlobalReader(tenantId, menus);
//        roleRepository.save(globalManager);
//        roleRepository.save(globalReader);
//    }

    private List<MenuDTO> getMenuDTOS() {

        SetMeal setMeal = tenantService.getSetMeal(RequestHeadHolder.get(RequestHeadHolder.RequestHead.TENANT_ID));
        return setMealClient.visibleMenuList(setMeal.getSetMealId()).getResult();
    }

    /**
     * 分页
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<Role> listRole(RoleListRequestParam param) {

        return roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotEmpty(param.getName())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + param.getName() + "%"));
            }
            return predicate;
        }, getPageable(param));
    }

    /**
     * 自增用户数
     *
     * @param roleIds
     */
    public void incrementSubscribers(List<String> roleIds) {

        synchronized (this) {
            List<Role> roles = roleRepository.findAllById(roleIds);
            roles.forEach(Role::incrementSubscribers);
            roleRepository.saveAll(roles);
        }
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     */
    public RoleDetailRepresentation detail(String id) {

        RoleDetailRepresentation roleDetail = roleRepository.getOne(id).toDetailRepresentation();

        List<MenuDTO> menus = getMenuDTOS();

        Map<String, MenuDTO> menuMap = reduceMenu(menus);

        matchMenu(roleDetail.getResources(), menuMap);

        return roleDetail;
    }

    private Map<String, MenuDTO> reduceMenu(List<MenuDTO> menus) {

        Map<String, MenuDTO> menuContainer = newHashMap();

        for (MenuDTO menu : menus) {

            menuContainer.put(menu.getMenuId(), menu);
            if (!menu.getChildren().isEmpty()) {
                menuContainer.putAll(reduceMenu(menu.getChildren()));
            }
        }
        return menuContainer;
    }

    private void matchMenu(List<AccessibleMenuRepresentation> resources, Map<String, MenuDTO> menuMap) {

        for (AccessibleMenuRepresentation resource : resources) {

            resource.setName(menuMap.get(resource.getId()).getName());
            if (!resource.getChildren().isEmpty()) {
                matchMenu(resource.getChildren(), menuMap);
            }
        }
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void delete(String id) {

        roleRepository.delete(roleRepository.getOne(id));
    }

    /**
     * 修改角色
     *
     * @param modifyCommand
     */
    public void modify(RoleModifyCommand modifyCommand) {

        Role role = roleRepository.getOne(modifyCommand.getId());
        accessibleMenuRepository.deleteAccessibleMenuByRole_Id(modifyCommand.getId());
        role.modify(modifyCommand);
        roleRepository.save(role);
    }

    public void decreaseSubscribers(List<String> roleIds) {

        synchronized (this) {
            List<Role> roles = roleRepository.findAllById(roleIds);
            roles.forEach(Role::decreaseSubscribers);
            roleRepository.saveAll(roles);
        }
    }

    public List<Role> findAllById(List<String> roleIds) {

        return roleRepository.findAllById(roleIds);
    }

    /**
     * 删除可见菜单
     *
     * @param menuIds 被删除的菜单id
     */
    public void clearInvalidMenu(String menuIds) {

        List<AccessibleMenu> menuList = accessibleMenuRepository.findByMenuId(menuIds);
        accessibleMenuRepository.deleteAll(menuList);
    }


    /**
     * 返回路由表
     * @param operatorId
     * @return
     */
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<RouterRepresentation> routes(String operatorId) {

        List<MenuDTO> menus = getMenuDTOS();

        List<RouterRepresentation> routes = getRoutes(operatorId, menus);

        Map<String, MenuDTO> menuDTOMap = reduceMenu(menus);

        perfectRoute(routes, menuDTOMap);

        return routes;
    }

    private void perfectRoute(List<RouterRepresentation> routes, Map<String, MenuDTO> menuMap) {

        for (RouterRepresentation router : routes) {

            MenuDTO menu = menuMap.get(router.getMeta().getId());

            router.getMeta().setTitle(menu.getName());
            router.getMeta().setIcon(menu.getIcon());
            router.setComponent(menu.getComponent());
            router.setRedirect(menu.getRedirect());
            router.setPath(menu.getPath());
            router.setHidden(menu.getHidden());

            if (!router.getChildren().isEmpty()) {
                perfectRoute(router.getChildren(), menuMap);
            }
        }
    }

    private List<RouterRepresentation> getRoutes(String operatorId, List<MenuDTO> menus) {

        AccountDetailRepresentation account = accountService.detail(operatorId);

        List<RouterRepresentation> routes;

        if (AccountLevel.ROOT.equals(account.getAccountLevel())) {

            routes = menus.stream().map(this::accessibleMenu).collect(Collectors.toList()).stream().map(AccessibleMenu::toRouter).collect(Collectors.toList());
        } else {

            Map<String, List<AccessibleMenu>> accessiblePropertiesMap = accessibleMenus(account)
                    .stream().collect(groupingBy(AccessibleMenu::getMenuId));


            List<AccessibleMenu> accessibleMenuList = menus.stream()
                    .filter(menu -> accessiblePropertiesMap.containsKey(menu.getMenuId()))
                    .map(menu -> this.convertAccessibleProperty(menu, accessiblePropertiesMap))
                    .collect(toList());

            routes = accessibleMenuList.stream().map(AccessibleMenu::toRouter).collect(Collectors.toList());
        }
        return routes;
    }

    private AccessibleMenu accessibleMenu(MenuDTO menu) {

        AccessibleMenu accessibleMenu = new AccessibleMenu();
        accessibleMenu.setMenuId(menu.getMenuId());
        accessibleMenu.setDelete(true);
        accessibleMenu.setView(true);
        accessibleMenu.setModify(true);
        accessibleMenu.setCreate(true);
        accessibleMenu.setId(gen32());

        if (Objects.nonNull(menu.getChildren())) {
            List<AccessibleMenu> children = menu.getChildren().stream().map(this::accessibleMenu).collect(Collectors.toList());
            accessibleMenu.setChildren(children);
        }
        return accessibleMenu;
    }

    private AccessibleMenu convertAccessibleProperty(MenuDTO menu, Map<String, List<AccessibleMenu>> accessiblePropertiesMap) {

        List<AccessibleMenu> accessibleProperty = accessiblePropertiesMap.get(menu.getMenuId());
        AccessibleMenu accessibleMenu = new AccessibleMenu();
        accessibleMenu.setMenuId(menu.getMenuId());
        accessibleMenu.setCreate(accessibleProperty.stream().anyMatch(AccessibleMenu::getCreate));
        accessibleMenu.setDelete(accessibleProperty.stream().anyMatch(AccessibleMenu::getDelete));
        accessibleMenu.setModify(accessibleProperty.stream().anyMatch(AccessibleMenu::getModify));
        accessibleMenu.setView(true);
        accessibleMenu.setChildren(menu.getChildren().stream().filter(menuDTO -> accessiblePropertiesMap.containsKey(menuDTO.getMenuId())).map(m -> convertAccessibleProperty(m, accessiblePropertiesMap)).collect(toList()));
        return accessibleMenu;
    }


    private List<AccessibleMenu> accessibleMenus(AccountDetailRepresentation account) {

        return accessibleMenuRepository.findByRole_IdIn(account.getRoleList().stream().map(RoleSimpleRepresentation::getId).collect(toList()));
    }

}
