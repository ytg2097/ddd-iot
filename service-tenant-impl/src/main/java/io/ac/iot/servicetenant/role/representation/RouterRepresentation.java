package io.ac.iot.servicetenant.role.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-08-28
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouterRepresentation {

    private String path;

    private Meta meta;

    private String component;

    private String redirect;

    private Boolean hidden;

    private List<RouterRepresentation> children;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta  {

        private String title;

        private String icon;

        private Boolean modifyAble;

        private Boolean createAble;

        private Boolean deleteAble;

        private Boolean viewAble;

        private String id;

    }
}
