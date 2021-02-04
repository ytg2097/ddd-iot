package io.ac.iot.servicebackedconfig.aggregation.menu;

import common.rest.RestResult;
import common.rest.ResultAssert;
import io.ac.iot.servicetenant.client.RoleClient;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:  菜单分布式事务处理
 * @author: yangtg
 * @create: 2020-12-23
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ATMenuService {

    private final MenuService menuService;
    private final RoleClient roleClient;

    /**
     *  删除本地数据
     *  同时删除租户服务中的关联菜单
     * @param id
     */
    @GlobalTransactional
    public void delete(String id){

        menuService.delete(id);

        RestResult restResult = roleClient.clearInvalidMenu(id);

        if (ResultAssert.assertFail(restResult)){
            try {
                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            } catch (TransactionException e) {
                log.info(e.getMessage());
            }
        }
    }
}
