package io.ac.iot.servicetenant.account;

import io.ac.iot.servicetenant.account.command.AccountCreateCommand;
import io.ac.iot.servicetenant.account.command.AccountModifyCommand;
import io.ac.iot.servicetenant.account.command.PasswordModifyCommand;
import io.ac.iot.servicetenant.account.factory.AccountFactory;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.account.model.AccountLevel;
import io.ac.iot.servicetenant.account.model.AccountStatus;
import io.ac.iot.servicetenant.account.model.AccountType;
import io.ac.iot.servicetenant.account.representation.AccountDetailRepresentation;
import io.ac.iot.servicetenant.account.request.AccountListRequestParam;
import io.ac.iot.servicetenant.exception.AccountInactiveException;
import io.ac.iot.servicetenant.exception.AccountNotExistsException;
import io.ac.iot.servicetenant.exception.AccountPasswordErrorException;
import io.ac.iot.servicetenant.exception.TenantInactiveException;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import io.ac.iot.servicetenant.role.RoleService;
import io.ac.iot.servicetenant.role.model.Role;
import io.ac.iot.servicetenant.tenant.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static io.ac.iot.servicetenant.common.util.PageUtil.getPageable;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AccountService {

    private final TenantService tenantService;
    private final AccountRepository accountRepository;
    @Autowired
    @Lazy
    private RoleService roleService;
    private final AccountFactory accountFactory;

    public AccountService(TenantService tenantService, AccountRepository accountRepository, AccountFactory accountFactory) {
        this.tenantService = tenantService;
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
    }


    public String createOrdinary(@Valid AccountCreateCommand createCommand) {

        Account account = accountFactory.createOrdinary(createCommand);
        roleService.incrementSubscribers(createCommand.getRoleIds());
        return save(account);
    }


    private String save(Account account) {

        accountRepository.save(account);
        return account.getId();
    }

    /**
     * 验证账号是否可以登录
     * 验证通过更新登陆信息
     *
     * @param loginName
     * @param password
     * @param accountType
     * @return
     */
    public AccountAuthRepresentation verifyLoginInfoAndMark(String loginName, String password, String accountType,String ip) {

        Account account = verify(loginName, password, accountType);

        saveLoginInfo(ip, account);

        return account.toAuthRepresentation();
    }

    private void saveLoginInfo(String ip, Account account) {

        account.updateLoginInfo(ip);
        accountRepository.save(account);
    }

    private Account verify(String loginName, String password, String accountType) {

        Account account = Optional.ofNullable(accountRepository.findByLoginNameEquals(loginName)).orElseThrow(AccountNotExistsException::new);

        if (!account.getPassword().equals(password)){
            throw new AccountPasswordErrorException();
        }

        if (!account.getAccountType().equals(AccountType.parse(accountType))) {
            throw new AccountNotExistsException();
        }

        if (!account.valid()) {
            throw new AccountInactiveException();
        }

        if (!tenantService.valid(account.getTenantId())){
            throw new TenantInactiveException();
        }
        return account;
    }

    /**
     * 作为租户激活事务中的一部分, 原子化激活租户下所有账号
     * saga补偿为重新失效
     *
     * @param tenantId
     */
    public void active(String tenantId) {

        List<Account> accounts = accountRepository.findByTenantIdEqualsAndStatusEquals(tenantId, AccountStatus.ACTIVE);
        if (accounts.isEmpty()) {
            return;
        }
        accounts.forEach(Account::active);
        accountRepository.saveAll(accounts);
        log.info("Activate all invalid accounts under the tenant , Tenant id: {}", tenantId);
    }

    /**
     * 作为租户不可用事务中的一部分, 原子化失效租户下所有账号
     * saga补偿为重新激活
     *
     * @param tenantId
     */
    public void inactive(String tenantId) {

        List<Account> accounts = accountRepository.findByTenantIdEqualsAndStatusEquals(tenantId, AccountStatus.INACTIVE);
        if (accounts.isEmpty()) {
            return;
        }
        accounts.forEach(Account::inactive);
        accountRepository.saveAll(accounts);
        log.info("All accounts under the invalid tenant , Tenant id: {}", tenantId);
    }

    /**
     * 账号列表
     * @param param
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<Account> listAccount(AccountListRequestParam param) {

        return accountRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtils.isNotEmpty(param.getLoginName())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("loginName"), "%" + param.getLoginName() + "%"));
            }
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("accountLevel"), AccountLevel.ORDINARY.ordinal()));
            return predicate;
        }, getPageable(param));
    }



    /**
     * 切换账号状态
     * @param id
     */
    public void toggleStatus(String id) {

        Account account = accountRepository.findById(id).get();
        account.toggle();
        accountRepository.save(account);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAccount(String id) {

        Account account = accountRepository.findById(id).get();
        account.destroy();
        roleService.decreaseSubscribers(account.getRoles().stream().map(Role::getId).collect(toList()));
        accountRepository.save(account);
    }

    /**
     * 获取租户Id
     * @param loginName
     * @return
     */
    public String getTenantId(String loginName) {

        return accountRepository.findByLoginNameEquals(loginName).getTenantId();
    }

    /**
     * 账号详细信息
     * @param accountId
     * @return
     */
    public AccountDetailRepresentation detail(String accountId) {

        return accountRepository.getOne(accountId).toDetailRepresentation();
    }

    /**
     * 修改账号信息
     * @param modifyCommand
     */
    public void modify(AccountModifyCommand modifyCommand) {

        Account account = accountRepository.getOne(modifyCommand.getId());
        account.modify(modifyCommand);

        roleService.decreaseSubscribers(account.getRoles().stream().map(Role::getId).collect(toList()));
        if (nonNull(modifyCommand.getRoleIds())){

            account.setRoles(roleService.findAllById(modifyCommand.getRoleIds()));
            roleService.incrementSubscribers(modifyCommand.getRoleIds());
        }
        accountRepository.save(account);
    }

    public void modifyPassword(PasswordModifyCommand modifyCommand) {

        Account account = accountRepository.getOne(modifyCommand.getId());
        account.modifyPassword(modifyCommand);

        accountRepository.save(account);
    }
}
