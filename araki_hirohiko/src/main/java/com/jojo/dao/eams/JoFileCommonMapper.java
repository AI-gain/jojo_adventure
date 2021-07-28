package com.jojo.dao.eams;

import com.jojo.po.FileCategory;
import com.jojo.po.JoFile;
import com.jojo.po.JoFileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JoFileCommonMapper {

    List<Map<String, Object>> getFileCategories();
}
