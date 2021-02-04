package io.ac.iot.servicetenant.tenant.factory;

import io.ac.iot.servicetenant.account.factory.AccountFactory;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.common.domain.model.Address;
import io.ac.iot.servicetenant.tenant.command.TenantCreateCommand;
import io.ac.iot.servicetenant.tenant.model.Tenant;
import org.springframework.stereotype.Component;

import static io.ac.iot.servicetenant.tenant.factory.TenantIdFactory.tenantId;
import static io.ac.iot.servicetenant.tenant.model.TenantStatus.CREATED;
import static java.lang.Boolean.TRUE;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Component
public class TenantFactory {

    private AccountFactory accountFactory;

    public TenantFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public Tenant create(TenantCreateCommand command) {

        Tenant entity = new Tenant();
        entity.setId(tenantId());
        entity.setName(command.getName());
        entity.setFixedPhone(command.getFixedPhone());
        entity.setEmail(command.getEmail());
        entity.setAddress(Address.of(command.getAddress()));
        entity.setStatus(CREATED);
        entity.setValid(TRUE);
        Account root = accountFactory.createRoot(command);
        root.setTenantId(entity.getId());
        entity.setAccount(root);
        return entity;
    }
}
