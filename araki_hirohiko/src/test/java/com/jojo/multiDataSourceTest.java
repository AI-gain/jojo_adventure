package com.jojo;
import com.alibaba.fastjson.JSON;
import com.jojo.bo.commonbean.Menus;
import com.jojo.service.common.IMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties={
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
public class multiDataSourceTest {

    @Autowired
    IMenuService menuService;

    @Test
    public void dataSourceTest() {
//        List<TableContent> tableInfoList = shineYueMapper.getTableMsg("CR_GR");
//        for (int i = 0; i < tableInfoList.size(); i++) {
//            System.out.println(tableInfoList.get(i).toString());
//        }
        List<Menus> menusList = menuService.getAllMenus();
        for (int i = 0; i < menusList.size(); i++) {
            System.out.println(JSON.toJSONString(menusList.get(i)));
        }

    }
}
