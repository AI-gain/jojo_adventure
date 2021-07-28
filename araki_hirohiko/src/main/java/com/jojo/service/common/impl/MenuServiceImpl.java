package com.jojo.service.common.impl;

import com.jojo.bo.commonbean.Menus;
import com.jojo.dao.master.MenuMapper;
import com.jojo.service.common.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menus> getBaseMenu() {
        return menuMapper.getBaseMenu();
    }


    @Override
    public List<Menus> getAllMenus() {
        List<Menus> baseMenuList = menuMapper.getBaseMenu();
        List<Menus> allMenus =  getSubMenus(baseMenuList);
        return allMenus;
    }

    public List<Menus> getSubMenus(List<Menus> menus) {
        List<Menus> subMenuList = new ArrayList<Menus>();
        for (int i = 0; i < menus.size(); i++) {
            subMenuList = menuMapper.getSubMenuByBaseMenu(menus.get(i).getKey());
            if (subMenuList.size() == 0) {
                continue;
            } else {
                menus.get(i).setSubMenus(subMenuList);
                getSubMenus(subMenuList);
            }
        }
        return menus;
    }
}
