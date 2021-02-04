package io.ac.iot.devicemodel.model.capability;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-03
 **/
@Data
@Entity
@Table(name = "device_capability_event")
public class Event  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
