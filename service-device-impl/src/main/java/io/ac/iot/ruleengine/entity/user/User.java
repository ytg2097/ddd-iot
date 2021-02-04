package io.ac.iot.ruleengine.entity.user;

import io.ac.iot.adapter.rest.command.UserCreateCommand;
import io.ac.iot.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Data
@Entity
public class User extends BaseEntity {

    @Id
    private String id;

    private String userName;

    private String phone;

    private String email;

    public static User create(UserCreateCommand command) {

        User user = new User();
        user.email = command.getEmail();
        user.phone = command.getPhone();
        user.userName = command.getUserName();
        user.id = gen32();
        return user;
    }
}
