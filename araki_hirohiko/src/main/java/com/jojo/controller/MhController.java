package com.jojo.controller;

import com.jojo.bo.commonbean.Menus;
import com.jojo.service.common.IMenuService;
import com.jojo.util.bean.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/jojo")
@RestController
public class MhController {

    @Autowired
    IMenuService menuService;

    private static Logger log = LoggerFactory.getLogger(MhController.class);

    @GetMapping("/getMenus")
    @ResponseBody
    public ResultData getMenus() {
        ResultData resultData = new ResultData();
        List<Menus> menusList = menuService.getAllMenus();
        resultData.setData(menusList);
        return resultData;
    }
}
