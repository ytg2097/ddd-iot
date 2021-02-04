package io.ac.iot.servicelog.tenantoperationlog.request;

import common.rest.BasePageQueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantOperationLogListRequestParam extends BasePageQueryParam {

    private String loginName;

    private String type;

    private String result;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
