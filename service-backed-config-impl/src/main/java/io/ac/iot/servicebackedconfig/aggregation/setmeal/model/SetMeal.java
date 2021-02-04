package io.ac.iot.servicebackedconfig.aggregation.setmeal.model;

import io.ac.iot.servicebackedconfig.common.domain.BaseEntity;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Entity
@Data
public class SetMeal extends BaseEntity {

    @Id
    private String id;

    /**
     * 价格
     */
    private Long price;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 套餐特性
     */
    @Embedded
    private Feature feature;

    /**
     * 可接入设备数量
     */
    private Long deviceNumber;

    /**
     * 可分配账号数量
     */
    private Integer accountNumber;

    /**
     * 单台设备命令调用次数上限
     */
    private Integer unitTimeCommandLimiter;

    /**
     * 是否启动
     */
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "parent_id",
            foreignKey = @ForeignKey(name = "NONE", value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<AccessibleMenu> accessibleMenuList;

    public void toggleEnableStatus() {

        this.enabled = !this.enabled;
    }

    public List<MenuDTO> menuList(){

        return childrenMenu(accessibleMenuList);
    }

    private List<MenuDTO> childrenMenu(List<AccessibleMenu> menus){

        return menus.stream()
                .map(menu -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setMenuId(menu.getMenu().getId());
                    menuDTO.setName(menu.getMenu().getName());
                    menuDTO.setComponent(menu.getMenu().getComponent());
                    menuDTO.setPath(menu.getMenu().getPath());
                    menuDTO.setIcon(menu.getMenu().getIcon());
                    menuDTO.setRedirect(menu.getMenu().getRedirect());
                    menuDTO.setHidden(menu.getMenu().getHidden());
                    menuDTO.setCreate(menu.getCreate());
                    menuDTO.setDelete(menu.getDelete());
                    menuDTO.setModify(menu.getModify());
                    menuDTO.setChildren(childrenMenu(menu.getMenus()));
                    return menuDTO;
                })
                .collect(Collectors.toList());
    }
}
