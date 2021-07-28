package com.jojo.service.util;

import com.jojo.bo.dbbean.TableContent;
import com.jojo.bo.dbbean.TableInfo;

import java.util.List;

public interface IShineYueDBService {
    /**
     *  通过数据库实例名查询当前实例下所有表信息
     * @param owner 数据库实例
     * @return 当前实例下的表信息
     */
    List<TableInfo> getShineYueYun4AllTables(String owner);

    /**
     *  通过表名查询表结构及字段信息
     * @param tableName 表名
     * @return 表结构及字段信息
     */
    List<TableContent> getTableMsg(String tableName);
}
