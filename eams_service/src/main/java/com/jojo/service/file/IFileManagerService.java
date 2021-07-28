package com.jojo.service.file;

import com.jojo.bo.JoFileField;
import com.jojo.po.JoFile;

import java.util.List;
import java.util.Map;

public interface IFileManagerService {

    Boolean saveFile(JoFileField file);

    JoFileField getFileInfo(Long fileId);

    List<Map<String, Object>> getFileCategories();

    List<JoFileField> getAllFiles();
}
