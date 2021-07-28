package com.jojo.dao.eams;

import com.jojo.po.JoFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoFileMapper {

    List<JoFile> getAllFiles();

    int insert(JoFile record);

    JoFile getFileById(Long fileId);

}
