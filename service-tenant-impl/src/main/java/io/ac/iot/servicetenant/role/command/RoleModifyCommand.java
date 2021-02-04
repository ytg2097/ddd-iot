package io.ac.iot.servicetenant.role.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-07-01
 **/
@Getter
@Setter
public class RoleModifyCommand {

    @NotNull
    private String id;

    @NotBlank
    private String name;

    private String description;

    private List<Menu> menuList;

    @Getter
    @Setter
    @Validated
    public static class Menu {

        @NotNull
        private String menuId;

        @NotNull
        private Boolean create;

        @NotNull
        private Boolean modify;

        @NotNull
        private Boolean view;

        @NotNull
        private Boolean delete;

        private List<Menu> children;

    }
}
