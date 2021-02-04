package io.ac.iot.servicelog.tenantoperationlog;

import common.rest.PageableRestResult;
import io.ac.iot.servicelog.tenantoperationlog.event.TenantOperationFinishEvent;
import io.ac.iot.servicelog.tenantoperationlog.model.OperationLogType;
import io.ac.iot.servicelog.tenantoperationlog.model.OperationResult;
import io.ac.iot.servicelog.tenantoperationlog.model.TenantOperationLog;
import io.ac.iot.servicelog.tenantoperationlog.representation.TenantOperationLogRepresentation;
import io.ac.iot.servicelog.tenantoperationlog.request.TenantOperationLogListRequestParam;
import io.ac.starter.util.RequestHeadHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-22
 **/
@Service
public class TenantOperationLogService {

    private TenantOperationLogRepository repository;
    private MongoTemplate mongoTemplate;

    public TenantOperationLogService(TenantOperationLogRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public String save(TenantOperationFinishEvent createCommand){

        TenantOperationLog log = TenantOperationLog.of(createCommand);
        repository.save(log);
        return log.getId();
    }

    public PageableRestResult list(TenantOperationLogListRequestParam requestParam){

        Query query = getQuery(requestParam);
        long count = mongoTemplate.count(query, TenantOperationLog.class);
        List<TenantOperationLogRepresentation> logs = mongoTemplate.find(query, TenantOperationLog.class).stream().map(TenantOperationLog::toRepresentation).collect(Collectors.toList());
        return new PageableRestResult(logs,count, getTotalPages(requestParam, count));
    }

    private int getTotalPages(TenantOperationLogListRequestParam requestParam, long count) {
        return count % requestParam.getPageSize() == 0 ? (int) (count / requestParam.getPageSize()) : (int) (count / requestParam.getPageSize() + 1);
    }

    private Query getQuery(TenantOperationLogListRequestParam requestParam){

        Criteria criteria = Criteria.where("tenantId").is(RequestHeadHolder.get(RequestHeadHolder.RequestHead.TENANT_ID));
        if (StringUtils.isNotBlank(requestParam.getLoginName()) && StringUtils.isNotBlank(requestParam.getLoginName().trim())){
            criteria.and("loginName").is(requestParam.getLoginName().trim());
        }

        if (Objects.nonNull(requestParam.getType())){

            criteria.and("type").is(OperationLogType.valueOf(requestParam.getType()));
        }

        if (Objects.nonNull(requestParam.getResult())){

            criteria.and("result").is(OperationResult.valueOf(requestParam.getResult()));
        }

        if (Objects.nonNull(requestParam.getBeginTime())){

            criteria.andOperator(Criteria.where("createTime").gte(requestParam.getBeginTime()),Criteria.where("createTime").lte(requestParam.getEndTime()));
        }

        Query query = new Query(criteria);
        query.skip((requestParam.getPageIndex() - 1) * requestParam.getPageSize()).limit(10);
        query.with(new Sort(Sort.Direction.DESC,"createTime"));
        return query;
    }
}
