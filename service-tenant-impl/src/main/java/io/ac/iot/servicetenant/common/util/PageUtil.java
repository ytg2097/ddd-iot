package io.ac.iot.servicetenant.common.util;

import common.rest.BasePageQueryParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-28
 **/
public class PageUtil {

    /**
     * 默认的分页对象，按照code 与title排序
     *
     * @param pageQueryParam 分页参数
     * @return
     */
    public static Pageable getPageable(BasePageQueryParam pageQueryParam) {
        return PageRequest.of(pageQueryParam.getPageIndex() - 1, pageQueryParam.getPageSize(), getSortForCreateTime());
    }

    /**
     * 默认的排序 按照时间排序
     *
     * @return
     */
    public static Sort getSortForCreateTime() {
        return new Sort(Sort.Direction.DESC, "createTime");
    }
}
