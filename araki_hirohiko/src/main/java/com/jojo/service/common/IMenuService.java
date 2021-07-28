package com.jojo.service.common;

import com.jojo.bo.commonbean.Menus;

import java.util.List;


public interface IMenuService {
    List<Menus> getBaseMenu();

    List<Menus> getAllMenus();
}
