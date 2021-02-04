package io.ac.iot.servicetenant.common.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @CreatedBy
    @Column(length = 32)
    private String createBy;
    @LastModifiedBy
    @Column(length = 32)
    private String updateBy;
    @CreatedDate
    private Instant createTime;
    @LastModifiedDate
    private Instant updateTime;
    @Column(columnDefinition = "bit default 1")
    private Boolean valid;

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
