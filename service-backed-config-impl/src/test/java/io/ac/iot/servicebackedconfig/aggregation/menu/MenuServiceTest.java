package io.ac.iot.servicebackedconfig.aggregation.menu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void delete() {

        menuService.delete("1");
    }
}
