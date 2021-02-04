package io.ac.iot.servicebackedconfig.aggregation.menu.root;

import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuCreateCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuModifyCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.MenuRepresentation;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.MenuSimpleRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-28
 **/
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    private String id;

    private String name;

    private String path;

    private String redirect;

    private String component;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "parent_id",
            foreignKey = @ForeignKey(name = "NONE", value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<Menu> children;

    private Integer sort;

    private String icon;

    @Column(name = "`root`")
    private Boolean root;

    private Boolean hidden;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Menu(String id) {
        this.id = id;
    }

    public static Menu create(MenuCreateCommand createCommand, boolean root) {

        return Menu.builder()
                .id(gen32())
                .name(createCommand.getName())
                .path(createCommand.getPath())
                .icon(createCommand.getIcon())
                .hidden(createCommand.getHidden())
                .sort(createCommand.getSort())
                .redirect(createCommand.getRedirect())
                .component(createCommand.getComponent())
                .root(root)
                .build();
    }

    public void addChildren(MenuCreateCommand createCommand) {

        this.children.add(create(createCommand,false));
    }

    public void modify(MenuModifyCommand modifyCommand) {

        name = modifyCommand.getName();
        sort = modifyCommand.getSort();
        icon = modifyCommand.getIcon();
        path = modifyCommand.getPath();
        hidden = modifyCommand.getHidden();
        redirect = modifyCommand.getRedirect();
        component = modifyCommand.getComponent();
    }

    public MenuSimpleRepresentation toSimpleRepresentation() {

        MenuSimpleRepresentation result = new MenuSimpleRepresentation();
        result.setId(id);
        result.setName(name);
        result.setChildren(children.stream().map(Menu::toSimpleRepresentation).collect(Collectors.toList()));
        return result;
    }

    public MenuRepresentation toRepresentation() {

        return MenuRepresentation.builder()
                .id(id)
                .name(name)
                .icon(icon)
                .path(path)
                .component(component)
                .redirect(redirect)
                .sort(sort)
                .hidden(hidden)
                .children(children.stream().sorted(Comparator.comparingInt(Menu::getSort)).map(Menu::toRepresentation).collect(Collectors.toList()))
                .build();
    }


}
