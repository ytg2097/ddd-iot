//package io.ac.iot.servicebackedconfig.common.domain;
//
//import io.ac.starter.DomainEventAwareAggregate;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import java.time.Instant;
//
///**
// * @description:
// * @author: yangtg
// * @create: 2020-04-02
// **/
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class  BaseDomainEventAwareAggregate extends DomainEventAwareAggregate {
//
//    @CreatedBy
//    @Column(length = 32)
//    private String createBy;
//    @LastModifiedBy
//    @Column(length = 32)
//    private String updateBy;
//    @CreatedDate
//    private Instant createTime;
//    @LastModifiedDate
//    private Instant updateTime;
//    private Boolean valid;
//
//    public void setValid(Boolean valid) {
//        this.valid = valid;
//    }
//}
