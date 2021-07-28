package com.jojo.service.util.impl;

import com.jojo.bo.dbbean.TableContent;
import com.jojo.bo.dbbean.TableInfo;
import com.jojo.dao.slaver.ShineYueMapper;
import com.jojo.service.util.IShineYueDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShineYueDBServiceImpl implements IShineYueDBService {

    @Autowired
    ShineYueMapper shineYueMapper;

    @Override
    public List<TableInfo> getShineYueYun4AllTables(String owner) {
        return shineYueMapper.getAllTables(owner);
    }

    @Override
    public List<TableContent> getTableMsg(String tableName) { return shineYueMapper.getTableMsg(tableName); }

}
