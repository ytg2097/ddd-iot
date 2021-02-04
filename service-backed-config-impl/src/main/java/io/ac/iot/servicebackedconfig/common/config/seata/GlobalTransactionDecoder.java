package io.ac.iot.servicebackedconfig.common.config.seata;

import common.JsonUtil;
import common.rest.RestResult;
import common.rest.ResultAssert;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-12-23
 **/
@Component
@Slf4j
public class GlobalTransactionDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        RestResult result = JsonUtil.fromReader(response.body().asReader(), RestResult.class);

        if (ResultAssert.assertFail(result)) {
            try {
                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            } catch (TransactionException e) {
                log.info(e.getMessage());
            }
        }

        return response;
    }
}
