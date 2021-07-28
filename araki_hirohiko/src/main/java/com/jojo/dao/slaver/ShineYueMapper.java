package com.jojo.dao.slaver;

import com.jojo.bo.dbbean.TableInfo;
import com.jojo.bo.dbbean.TableContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShineYueMapper {

    List<TableInfo> getAllTables(String owner);

    List<TableContent> getTableMsg(String tableName);
}
