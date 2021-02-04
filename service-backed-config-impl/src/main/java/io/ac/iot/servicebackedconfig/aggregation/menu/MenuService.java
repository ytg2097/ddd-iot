package io.ac.iot.servicebackedconfig.aggregation.menu;

import common.exception.BusinessException;
import common.rest.RestResult;
import common.rest.ResultAssert;
import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuCreateCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuModifyCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.MenuRepresentation;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.MenuSimpleRepresentation;
import io.ac.iot.servicebackedconfig.aggregation.menu.root.Menu;
import io.ac.iot.servicetenant.client.RoleClient;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-25
 **/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MenuService {

    private final MenuRepository menuRepository;
    private final RoleClient roleClient;
    /**
     * 所有菜单
     * @return
     */
    public List<MenuSimpleRepresentation> simpleRepresentation(){

        return menuRepository.findByRootIsTrueOrderBySort()
                .stream()
                .map(Menu::toSimpleRepresentation).collect(toList());
    }

    public void create(MenuCreateCommand createCommand) {

        if (createCommand.getParentId() != null){
            Menu parent = menuRepository.getOne(createCommand.getParentId());
            parent.addChildren(createCommand);
            menuRepository.save(parent);
        }else {
            Menu menu = Menu.create(createCommand,true);
            menuRepository.save(menu);
        }
    }

    public void modify(MenuModifyCommand modifyCommand){

        Menu one = menuRepository.getOne(modifyCommand.getId());
        one.modify(modifyCommand);
        menuRepository.save(one);
    }

    public void delete(String id) {

        Menu menu = menuRepository.findById(id).get();

        menuRepository.delete(menu);
    }

    public List<MenuRepresentation> all() {

        return menuRepository.findByRootIsTrueOrderBySort()
                .stream()
                .map(Menu::toRepresentation)
                .collect(toList());
    }

    public List<Menu> findRoot() {

        return menuRepository.findByRootIsTrueOrderBySort();
    }

    public List<Menu> findAll() {

        return menuRepository.findAll();
    }
}
