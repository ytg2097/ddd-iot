package io.ac.iot.servicetenant.account.factory;

import io.ac.iot.servicetenant.account.command.AccountCreateCommand;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.account.model.AccountType;
import io.ac.iot.servicetenant.role.model.Role;
import io.ac.iot.servicetenant.tenant.command.TenantCreateCommand;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;
import static io.ac.iot.servicetenant.account.model.AccountLevel.ORDINARY;
import static io.ac.iot.servicetenant.account.model.AccountLevel.ROOT;
import static io.ac.iot.servicetenant.account.model.AccountStatus.ACTIVE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Component
public class AccountFactory {

    private Account create(AccountCreateCommand createCommand){

        Account entity = new Account();
        entity.setId(gen32());
        entity.setStatus(ACTIVE);
        entity.setTenantId(createCommand.getTenantId());
        entity.setLoginName(createCommand.getLoginName());
        entity.setPassword(createCommand.getPassword());
        entity.setAccountType(AccountType.parse(createCommand.getAccountType()));
        entity.setUserName(createCommand.getUserName());
        entity.setPhone(createCommand.getPhone());
        entity.setEmail(createCommand.getEmail());
        entity.setValid(TRUE);
        return entity;
    }

    public Account createOrdinary(AccountCreateCommand createCommand) {

        Account account = create(createCommand);
        account.setAccountLevel(ORDINARY);
        if(nonNull(createCommand.getRoleIds())){
            account.setRoles(createCommand.getRoleIds().stream().map(Role::new).collect(Collectors.toList()));
        }
        return account;
    }

    public Account createRoot(TenantCreateCommand command) {

        Account entity = new Account();
        entity.setId(gen32());
        entity.setStatus(ACTIVE);
        entity.setLoginName(command.getAccount().getLoginName());
        entity.setPassword("123456");
        entity.setAccountType(AccountType.PLATFORM);
        entity.setUserName(command.getAccount().getUserName());
        entity.setPhone(command.getAccount().getPhone());
        entity.setEmail(command.getAccount().getEmail());
        entity.setValid(TRUE);
        entity.setAccountLevel(ROOT);
        return entity;
    }
}
