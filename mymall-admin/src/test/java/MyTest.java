package com.mymall;

import com.mymall.test.sys.entity.SysMenu;
import com.mymall.test.sys.service.impl.SysMenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

//@ContextConfiguration
@SpringBootTest
public class MyTest {

    @Autowired
    SysMenuServiceImpl sysMenuService;

    @Test
    public void test1() {

//        String rperms=null;
        List<SysMenu> menuByUserId = sysMenuService.getMenuByUserId(1L);
        System.out.println(menuByUserId.size());
        menuByUserId.forEach(item->{
            System.out.println(item.toString());

        });
        String rperms= menuByUserId.stream().map(SysMenu::getPerms).collect(Collectors.joining(","));

        System.out.println(rperms);

    }


}
