package io.ac.iot.common.util;

import common.rest.PageableRestResult;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
public class PageUtil {

    public static <S,T> PageableRestResult<T> map(Page<S> page, Function<S,T> mapper){

        List<T> data = page.get().map(mapper).collect(toList());
        return new PageableRestResult<>(data, page.getTotalElements(), page.getTotalPages());
    }
}
