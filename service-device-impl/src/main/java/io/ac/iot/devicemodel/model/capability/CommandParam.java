package io.ac.iot.devicemodel.model.capability;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-01
 **/
@Data
public class CommandParam {

    private String name;

    private DataType dataType;

    private Boolean required;

    private Integer min;

    private Integer max;

    private Integer maxLength;

    private String unit;

    private List<String> enumList;
}
