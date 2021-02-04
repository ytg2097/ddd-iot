package io.ac.iot.common.util;

import common.rest.BasePageQueryParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
public class JpaPageable {

    public static PageRequest of(BasePageQueryParam param){

        return PageRequest.of(param.getPageIndex() - 1, param.getPageSize(), TIME_ORDER);
    }

    public final static Sort TIME_ORDER = Sort.by(Sort.Direction.DESC, "createTime");

}
