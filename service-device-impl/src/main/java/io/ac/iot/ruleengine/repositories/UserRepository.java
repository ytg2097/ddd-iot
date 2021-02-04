package io.ac.iot.ruleengine.repositories;

import io.ac.iot.ruleengine.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
}
