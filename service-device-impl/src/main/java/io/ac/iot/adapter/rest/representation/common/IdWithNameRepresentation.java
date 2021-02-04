package io.ac.iot.adapter.rest.representation.common;

import common.exception.SystemException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-13
 **/
@Getter
@Setter
public class IdWithNameRepresentation {

    private String id;

    private String name;

    public  static List<IdWithNameRepresentation> newInstance(List<?> entityList){

        return entityList.stream().map(IdWithNameRepresentation::of).collect(toList());
    }

    public  static IdWithNameRepresentation of(Object entity){

        return of(entity,"id","name");
    }

    public static IdWithNameRepresentation of(Object entity,String idField,String nameField){

        IdWithNameRepresentation result = new IdWithNameRepresentation();
        try {
            result.id = entity.getClass().getField(idField).get(entity).toString();
            result.name = entity.getClass().getField(nameField).get(entity).toString();
            return result;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
