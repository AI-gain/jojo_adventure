package com.jojo.dao.master;

import com.jojo.bo.commonbean.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menus> getBaseMenu();

    List<Menus> getSubMenuByBaseMenu(@Param("baseMenu") String code);
}
