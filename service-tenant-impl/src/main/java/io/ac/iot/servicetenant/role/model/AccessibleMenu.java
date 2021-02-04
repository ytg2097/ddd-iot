package io.ac.iot.servicetenant.role.model;

import io.ac.iot.servicetenant.role.representation.AccessibleMenuRepresentation;
import io.ac.iot.servicetenant.role.representation.RouterRepresentation;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Data
@Entity
public class AccessibleMenu {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessibleMenu that = (AccessibleMenu) o;
        return menuId.equals(that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId);
    }

    @Id
    private String id;

    private String menuId;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Role role;

    @Column(name = "\"create\"")
    private Boolean create;

    @Column(name = "\"modify\"")
    private Boolean modify;

    @Column(name = "\"view\"")
    private Boolean view;

    @Column(name = "\"delete\"")
    private Boolean delete;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "parent_id",
            foreignKey = @ForeignKey(name = "NONE", value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<AccessibleMenu> children;

    public AccessibleMenuRepresentation toRepresentation() {

        AccessibleMenuRepresentation result = new AccessibleMenuRepresentation();
        result.setId(menuId);
        result.setCreate(create);
        result.setDelete(delete);
        result.setModify(modify);
        result.setView(view);
        result.setChildren(children.stream().map(AccessibleMenu::toRepresentation).collect(toList()));
        return result;
    }

    public RouterRepresentation toRouter() {

        return RouterRepresentation.builder()
                .meta(RouterRepresentation.Meta.builder()
                        .id(menuId)
                        .createAble(create)
                        .modifyAble(modify)
                        .deleteAble(delete)
                        .viewAble(view)
                        .build())
                .children(children.stream().map(this::children).collect(toList()))
                .build();
    }

    private RouterRepresentation children(AccessibleMenu menu){

        return RouterRepresentation.builder()
                .meta(RouterRepresentation.Meta.builder()
                        .id(menu.getMenuId())
                        .createAble(menu.getCreate())
                        .modifyAble(menu.getModify())
                        .deleteAble(menu.getDelete())
                        .viewAble(menu.getView())
                        .build())
                .children(menu.getChildren().stream().map(this::children).collect(toList()))
                .build();
    }
}
