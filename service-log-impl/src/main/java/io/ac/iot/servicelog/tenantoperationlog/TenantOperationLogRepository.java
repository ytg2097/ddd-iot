package io.ac.iot.servicelog.tenantoperationlog;

import io.ac.iot.servicelog.tenantoperationlog.model.TenantOperationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-22
 **/
public interface TenantOperationLogRepository extends MongoRepository<TenantOperationLog,String> {


}
