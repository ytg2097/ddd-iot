package io.ac.iot.manufacturer.model;

import io.ac.iot.adapter.rest.command.ManufacturerCreateCommand;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import static common.util.ObjectUtil.copyField;
import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Data
@Entity
public class Manufacturer {

    @Id
    private String id;

    private String name;

    private String description;

    private String phone;

    private String email;

    public static Manufacturer create(ManufacturerCreateCommand createCommand){

        Manufacturer manufacturer = copyField(createCommand, new Manufacturer());
        manufacturer.setId(gen32());
        return manufacturer;
    }
}
