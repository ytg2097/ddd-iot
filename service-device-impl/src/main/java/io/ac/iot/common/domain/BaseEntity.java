package io.ac.iot.common.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String updateBy;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;

    public String tenantId;
}
