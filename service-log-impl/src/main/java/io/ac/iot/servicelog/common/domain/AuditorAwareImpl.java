package io.ac.iot.servicelog.common.domain;

import io.ac.starter.util.RequestHeadHolder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        String operatorId = RequestHeadHolder.find(RequestHeadHolder.RequestHead.OPERATOR_ID);
        return Optional.ofNullable(operatorId);
    }
}
