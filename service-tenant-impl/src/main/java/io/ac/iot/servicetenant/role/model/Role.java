package io.ac.iot.servicetenant.role.model;

import io.ac.iot.servicetenant.common.domain.BaseEntity;
import io.ac.iot.servicetenant.role.command.RoleModifyCommand;
import io.ac.iot.servicetenant.role.representation.RoleDetailRepresentation;
import io.ac.iot.servicetenant.role.representation.RoleRepresentation;
import io.ac.iot.servicetenant.role.representation.RoleSimpleRepresentation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
@Data
@Entity
@NoArgsConstructor
public class Role extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String description;

    private Integer subscribers;

    private String tenantId;

    private RoleLevel roleLevel;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private List<AccessibleMenu> accessibleMenuList;

    public RoleRepresentation toRepresentation() {

        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setId(id);
        roleRepresentation.setDescription(description);
        roleRepresentation.setName(name);
        roleRepresentation.setSubscribers(subscribers);
        roleRepresentation.setRoleLevel(roleLevel.name());
        roleRepresentation.setCreateTime(Date.from(getCreateTime()));
        roleRepresentation.setLastModifyTime(Date.from(getUpdateTime()));
        return roleRepresentation;
    }

    public RoleSimpleRepresentation toSimpleRepresentation() {

        RoleSimpleRepresentation roleSimpleRepresentation = new RoleSimpleRepresentation();
        roleSimpleRepresentation.setId(id);
        roleSimpleRepresentation.setName(name);
        return roleSimpleRepresentation;
    }

    public Role(String id){

        this.id = id;
    }

    public void incrementSubscribers() {

        this.setSubscribers(subscribers + 1);
    }

    public void decreaseSubscribers() {

        this.setSubscribers(subscribers - 1);
    }

    public RoleDetailRepresentation toDetailRepresentation() {

        RoleDetailRepresentation detail = new RoleDetailRepresentation();

        detail.setId(id);
        detail.setName(name);
        detail.setDescription(description);
        detail.setResources(accessibleMenuList.stream().map(AccessibleMenu::toRepresentation).collect(Collectors.toList()));
        return detail;
    }

    public void modify(RoleModifyCommand modifyCommand) {

        this.name = modifyCommand.getName();
        this.description = modifyCommand.getDescription();
        this.setAccessibleMenuList(
                nonNull(modifyCommand.getMenuList())
                        ?
                            modifyCommand.getMenuList().stream().filter(RoleModifyCommand.Menu::getView).map(menu -> this.accessibleMenu(menu,this)).collect(Collectors.toList())
                        :
                            emptyList()
        );
    }

    private AccessibleMenu accessibleMenu(RoleModifyCommand.Menu menu, Role role) {

        AccessibleMenu accessibleMenu = new AccessibleMenu();
        accessibleMenu.setMenuId(menu.getMenuId());
        accessibleMenu.setDelete(Optional.ofNullable(menu.getDelete()).orElse(false));
        accessibleMenu.setView(true);
        accessibleMenu.setModify(Optional.ofNullable(menu.getModify()).orElse(false));
        accessibleMenu.setCreate(Optional.ofNullable(menu.getCreate()).orElse(false));
        accessibleMenu.setRole(role);
        accessibleMenu.setId(gen32());

        if (Objects.nonNull(menu.getChildren())) {
            List<AccessibleMenu> children = menu.getChildren().stream().filter(RoleModifyCommand.Menu::getView).map(mm -> this.accessibleMenu(mm, role)).collect(Collectors.toList());
            accessibleMenu.setChildren(children);
        }
        return accessibleMenu;
    }
}
