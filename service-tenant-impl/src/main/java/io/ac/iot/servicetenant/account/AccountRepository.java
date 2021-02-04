package io.ac.iot.servicetenant.account;


import io.ac.iot.servicetenant.common.domain.BaseEntityRepository;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.account.model.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
public interface AccountRepository extends JpaRepository<Account,String>, BaseEntityRepository<Account,String>,JpaSpecificationExecutor<Account> {

    /**
     * 根据用户名和密码查询
     * @param loginName
     * @return
     */
    Account findByLoginNameEquals(String loginName);

    /**
     * 查询指定租户下指定状态的账号
     * @param tenantId
     * @param status
     * @return
     */
    List<Account> findByTenantIdEqualsAndStatusEquals(String tenantId, AccountStatus status);

    /**
     * 分页
     * @param var1
     * @param var2
     * @return
     */
    @Override
    Page<Account> findAll(Specification<Account> var1, Pageable var2);


}
