package io.ac.iot.servicetenant.account.representation;

import io.ac.iot.servicetenant.account.model.AccountLevel;
import io.ac.iot.servicetenant.account.model.AccountType;
import io.ac.iot.servicetenant.role.representation.RoleSimpleRepresentation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Getter
@Setter
public class AccountDetailRepresentation {

    private String id;

    private String userName;

    private String phone;

    private String email;

    private String loginName;

    private AccountType accountType;

    private AccountLevel accountLevel;

    private List<RoleSimpleRepresentation> roleList;

}
