package io.ac.iot.servicetenant.account.model;

import common.exception.BusinessException;
import common.util.ObjectUtil;
import io.ac.iot.servicetenant.account.command.AccountCreateCommand;
import io.ac.iot.servicetenant.account.command.AccountModifyCommand;
import io.ac.iot.servicetenant.account.command.PasswordModifyCommand;
import io.ac.iot.servicetenant.account.representation.AccountDetailRepresentation;
import io.ac.iot.servicetenant.account.representation.AccountRepresentation;
import io.ac.iot.servicetenant.common.Constants;
import io.ac.iot.servicetenant.common.domain.BaseEntity;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import io.ac.iot.servicetenant.role.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static common.util.UUIDGenerator.gen32;
import static io.ac.iot.servicetenant.account.model.AccountLevel.ORDINARY;
import static io.ac.iot.servicetenant.account.model.AccountLevel.ROOT;
import static io.ac.iot.servicetenant.account.model.AccountStatus.ACTIVE;
import static io.ac.iot.servicetenant.account.model.AccountStatus.INACTIVE;
import static io.ac.iot.servicetenant.exception.code.AccountExceptionCode.PASSWORD_ERROR;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Where(clause = "valid=" + Constants.FLAG_NOT_DELETED )
public class Account extends BaseEntity {

    @Id
    private String id;

    @Column(length = 32)
    private String loginName;

    @Column(length = 32)
    private String password;

    @Column(length = 32)
    private String tenantId;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountLevel accountLevel;

    private String userName;

    private String phone;

    private String email;

    private String lastLoginIp;

    private Date lastLoginTime;

    @Where(clause = "valid = 1")
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))}
    )
    private List<Role> roles;




    public boolean valid(){

        return status.equals(ACTIVE);
    }

    public void inactive(){

        this.status = INACTIVE;
    }

    public void active(){

        this.status = ACTIVE;
    }

    public void destroy(){

        this.setValid(null);
    }

    public void updateLoginInfo(String ip){
        this.lastLoginIp = ip;
        this.lastLoginTime = new Date();
    }

    public void toggle() {

        this.status = this.status.equals(ACTIVE)? INACTIVE : ACTIVE;
    }

    public AccountAuthRepresentation toAuthRepresentation() {

        return ObjectUtil.copyField(this,new AccountAuthRepresentation());
    }

    public AccountRepresentation toRepresentation() {

        AccountRepresentation representation = new AccountRepresentation();
        representation.setRule(roles.stream().map(Role::getName).collect(joining(",")));
        representation.setLastLoginIp(lastLoginIp);
        representation.setLastLoginTime(lastLoginTime);
        representation.setLoginName(loginName);
        representation.setStatus(status.name);
        representation.setId(id);
        representation.setLevel(accountLevel.name());
        return representation;
    }

    public AccountDetailRepresentation toDetailRepresentation() {

        AccountDetailRepresentation detail = new AccountDetailRepresentation();

        detail.setEmail(email);
        detail.setId(id);
        detail.setPhone(phone);
        detail.setUserName(userName);
        detail.setAccountType(accountType);
        detail.setAccountLevel(accountLevel);
        detail.setLoginName(loginName);
        detail.setRoleList(roles.stream().map(Role::toSimpleRepresentation).collect(Collectors.toList()));
        return detail;
    }

    public void modify(AccountModifyCommand modifyCommand) {

        this.email = modifyCommand.getEmail();
        this.phone = modifyCommand.getPhone();
        this.userName = modifyCommand.getUserName();
    }

    public void modifyPassword(PasswordModifyCommand modifyCommand) {

        if (!password.equals(modifyCommand.getOldPassword())){
            throw new BusinessException(PASSWORD_ERROR);
        }
        this.password = modifyCommand.getNewPassword();
    }
}
