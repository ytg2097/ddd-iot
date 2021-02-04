package io.ac.iot.servicetenant.common.domain;

import io.ac.iot.servicetenant.common.util.RequestIdHolder;
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

        return Optional.ofNullable(RequestIdHolder.opId.find());
    }
}
