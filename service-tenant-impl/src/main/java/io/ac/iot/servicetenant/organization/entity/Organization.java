package io.ac.iot.servicetenant.organization.entity;

import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Entity
@Data
public class Organization extends BaseEntity {

    @Id
    private String id;

    private String name;

    /**
     * 所属租户id
     */
    private String tenantId;

    @OneToOne
    @JoinColumn(
            name = "manager_id",
            foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)
    )
    private Account manager;

}
