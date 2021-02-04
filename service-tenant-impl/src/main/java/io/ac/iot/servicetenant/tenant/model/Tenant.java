package io.ac.iot.servicetenant.tenant.model;

import common.util.ObjectUtil;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.command.TenantSetUpSetMealCommand;
import io.ac.iot.servicetenant.common.Constants;
import io.ac.iot.servicetenant.common.domain.BaseEntity;
import io.ac.iot.servicetenant.common.domain.model.Address;
import io.ac.iot.servicetenant.representation.TenantRepresentation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.util.Objects;

import static io.ac.iot.servicetenant.tenant.model.TenantStatus.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 *              租户  <====>  root账户
 *               ↓
 *       List<Organization>
 *
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Where(clause = "valid=" + Constants.FLAG_NOT_DELETED + " ")
public class Tenant extends BaseEntity {

    @Id
    @Column(length = 32)
    private String id;

    private String name;

    @Embedded
    private Address address;

    private String email;

    private String fixedPhone;

    @Enumerated(EnumType.STRING)
    private TenantStatus status;

    /**
     * 新增租户时同时注册一个root权限账号
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(
            name = "account_id",
            foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
    )
    private Account account;

    /**
     * 套餐
     */
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(
            name = "setmeal_id",
            foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
    )
    private SetMeal setMeal;

    public TenantRepresentation toRepresentation(){

        return ObjectUtil.copyField(this,new TenantRepresentation());
    }

    /**
     * 激活租户
     */
    public void active(){

        this.setStatus(ACTIVE);
    }

    /**
     * 删除这个租户
     */
    public void delete(){

        this.setValid(FALSE);
    }

    /**
     * 从删除状态找回
     */
    public void revive(){

        this.setValid(TRUE);
    }

    /**
     * 锁定这个租户
     */
    public void lock(){

        this.setStatus(LOCKED);
    }

    /**
     * 使租户失效
     */
    public void inactive(){

        this.setStatus(INACTIVE);
    }

    /**
     * 账号是否有效
     * @return
     */
    public Boolean valid() {

        return ACTIVE.equals(this.status);
    }

    /**
     * 设置套餐
     * @param command
     */
    public void setUpSetMeal(TenantSetUpSetMealCommand command) {

        if (Objects.isNull(setMeal)){
            this.setMeal = SetMeal.of(command.getSetMealId(),command.getDate());
        }else if (setMeal.getSetMealId().equals(command.getSetMealId())){
            this.setMeal.setSetMealId(command.getSetMealId());
        }else {
            this.setMeal.renew(command.getDate());
        }
    }
}
