package io.ac.iot.servicetenant.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.common.util.StringUtils;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-12-23
 **/
@Component
@Slf4j
public class GlobalTransactionXIDInjector implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        String xid = RootContext.getXID();
        if (StringUtils.isNotBlank(xid)) {
            template.header("TX_XID", xid);
            log.info("Put XID {} into Feign request header",xid);
        }
    }
}
