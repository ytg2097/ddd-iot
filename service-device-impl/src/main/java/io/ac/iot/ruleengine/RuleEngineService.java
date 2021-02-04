package io.ac.iot.ruleengine;

import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.iot.ruleengine.entity.user.User;
import io.ac.iot.ruleengine.factory.RuleEngineFactory;
import io.ac.iot.ruleengine.repositories.UserRepository;
import io.ac.iot.adapter.rest.command.RuleCreateCommand;
import io.ac.iot.adapter.rest.command.UserCreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class RuleEngineService {

    private final RuleEngineFactory ruleFactory;
    private final RuleEngineRepository ruleRepository;
    private final UserRepository userRepository;

    public String create(RuleCreateCommand command){

        RuleEngine rule = ruleFactory.create(command);
        ruleRepository.doSave(rule);
        return rule.getId();
    }

    /**
     * 切换启用状态
     */
    public void changeState(String ruleId){

        RuleEngine rule = ruleRepository.getOne(ruleId);
        rule.changeEnableState();
        ruleRepository.doSave(rule);
    }

    /**
     * 新增用户  用于接收短信  邮件
     * @param command
     * @return
     */
    public String addUser(UserCreateCommand command){

        User user = User.create(command);
        userRepository.save(user);
        return user.getId();
    }
}
