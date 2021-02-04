package io.ac.iot.servicebackedconfig.aggregation.setmeal.model;

import io.ac.iot.servicebackedconfig.aggregation.menu.root.Menu;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return menu.getId().equals(that.menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu.getId());
    }

    @Id
    private String id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Menu menu;

    private String setMealId;

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
    private List<AccessibleMenu> menus;


//    public AccessibleMenuRepresentation toRepresentation() {
//
//        AccessibleMenuRepresentation result = new AccessibleMenuRepresentation();
//        result.setId(menu.getId());
//        result.setName(menu.getName());
//        result.setCreate(create);
//        result.setDelete(delete);
//        result.setModify(modify);
//        result.setView(view);
//        result.setChildren(children.stream().map(AccessibleMenu::toRepresentation).collect(toList()));
//        return result;
//    }
//
//    public RouterRepresentation toRouter() {
//
//        return RouterRepresentation.builder()
//                .component(menu.getComponent())
//                .redirect(menu.getRedirect())
//                .meta(RouterRepresentation.Meta.builder()
//                        .title(menu.getName())
//                        .icon(menu.getIcon())
//                        .createAble(create)
//                        .modifyAble(modify)
//                        .deleteAble(delete)
//                        .viewAble(view)
//                        .build())
//                .path(menu.getPath())
//                .hidden(menu.getHidden())
//                .children(children.stream().map(this::children).collect(toList()))
//                .build();
//    }
//
//    private RouterRepresentation children(AccessibleMenu menu){
//
//        return RouterRepresentation.builder()
//                .meta(RouterRepresentation.Meta.builder()
//                        .title(menu.getMenu().getName())
//                        .icon(menu.getMenu().getIcon())
//                        .createAble(menu.getCreate())
//                        .modifyAble(menu.getModify())
//                        .deleteAble(menu.getDelete())
//                        .viewAble(menu.getView())
//                        .build())
//                .component(menu.getMenu().getComponent())
//                .redirect(menu.getMenu().getRedirect())
//                .path(menu.getMenu().getPath())
//                .hidden(menu.getMenu().getHidden())
//                .children(menu.getChildren().stream().map(this::children).collect(toList()))
//                .build();
//    }
}
