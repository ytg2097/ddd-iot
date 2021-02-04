package io.ac.iot.servicelog.common.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
public abstract class BaseEntity {

    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String updateBy;
    @CreatedDate
    private Instant createTime;
    @LastModifiedDate
    private Instant updateTime;

    private Boolean valid;

    protected void setValid(Boolean valid) {
        this.valid = valid;
    }
}
