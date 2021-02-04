package io.ac.iot.servicedatahub.aggregation.devicemodel.capability;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-03
 **/
@Data
@Entity
@Table(name = "device_capability_command")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private List<CommandParam> params;

    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private List<CommandParam> response;
}
