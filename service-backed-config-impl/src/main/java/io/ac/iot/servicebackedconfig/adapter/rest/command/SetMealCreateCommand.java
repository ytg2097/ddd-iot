package io.ac.iot.servicebackedconfig.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Getter
@Setter
public class SetMealCreateCommand {

    @NotNull
    private String name;

    @NotNull
    @Min(0)
    private Long price;

    @NotNull
    @Min(0)
    private Integer unitTimeCommandLimiter;

    @NotNull
    @Min(0)
    private Long deviceNumber;

    @NotNull
    @Min(0)
    private Long accountNumber;

    @Valid
    private Feature feature;

    private List<@Valid Menu> menuList;


    @Setter
    @Getter
    public static class Feature{

        @NotNull
        private Boolean employee;

        @NotNull
        private Boolean ticket;

        @NotNull
        private Boolean gps;

        @NotNull
        private Boolean msg;
    }

    @Getter
    @Setter
    @Validated
    public static class Menu{

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
