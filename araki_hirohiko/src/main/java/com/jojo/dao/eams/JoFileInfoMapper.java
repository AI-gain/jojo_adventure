package com.jojo.dao.eams;

import com.jojo.po.JoFile;
import com.jojo.po.JoFileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoFileInfoMapper {

    int insert(JoFileInfo record);

    JoFileInfo getFileInfoById(Long fileId);
}
