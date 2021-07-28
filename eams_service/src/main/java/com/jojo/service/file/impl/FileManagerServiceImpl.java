package com.jojo.service.file.impl;

import com.jojo.bo.JoFileField;
import com.jojo.dao.eams.JoFileCommonMapper;
import com.jojo.dao.eams.JoFileInfoMapper;
import com.jojo.dao.eams.JoFileMapper;
import com.jojo.po.JoFile;
import com.jojo.po.JoFileInfo;
import com.jojo.service.file.IFileManagerService;
import com.jojo.util.guid.IdWorker;
import com.jojo.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileManagerServiceImpl implements IFileManagerService {

    @Autowired
    JoFileMapper joFileMapper;

    @Autowired
    JoFileInfoMapper joFileInfoMapper;

    @Autowired
    JoFileCommonMapper joFileCommonMapper;

    @Autowired
    RedisUtil redisUtil;

    Logger log = LoggerFactory.getLogger(FileManagerServiceImpl.class);

    @Override
    public Boolean saveFile(JoFileField file) {
        JoFile joFile = new JoFile();
        IdWorker idWorker = new IdWorker(0, 0);
        Long fileId = idWorker.nextId();
        joFile.setFileid(fileId);
        joFile.setFilename(file.getFileName());
        joFile.setFiletype(file.getFileType());
        joFile.setFilesize(file.getFileSize());
        joFile.setFilepath(file.getFilePath());
        joFile.setCreatedate(file.getCreateDate());
        joFile.setCreateuser(file.getCreateUser());
        int joFileResult = joFileMapper.insert(joFile);
        JoFileInfo joFileInfo = new JoFileInfo();
        joFileInfo.setFileid(fileId);
        joFileInfo.setFiledes(file.getFileDes());
        joFileInfo.setFileinfo(file.getFileInfo());
        joFileInfo.setFilecategory(file.getFileCategory());
        joFileInfo.setFilecovers(file.getFileCovers());
        int joFileInfoResult = joFileInfoMapper.insert(joFileInfo);
        redisUtil.set(joFile.getFileid().toString(), joFile.getFilepath());
        log.info("文件保存成功 ==> {}, 文件信息保存成功 ==> {}", joFileResult, joFileInfoResult);
        return true;
    }

    @Override
    public JoFileField getFileInfo(Long fileId) {
        JoFile joFile = joFileMapper.getFileById(fileId);
        JoFileField joFileField = new JoFileField();
        joFileField.setFilePath(joFile.getFilepath());
        joFileField.setFileName(joFile.getFilename());
        return joFileField;
    }

    @Override
    public List<Map<String, Object>> getFileCategories() {
        List<Map<String, Object>> categoryList = new ArrayList<>();
        categoryList = joFileCommonMapper.getFileCategories();
        return categoryList;
    }

    @Override
    public List<JoFileField> getAllFiles() {
        List<JoFile> joFileList = joFileMapper.getAllFiles();
        List<JoFileField> joFileFieldList = new ArrayList<>();
        for (JoFile joFile: joFileList) {
            JoFileField joFileField = new JoFileField();
            joFileField.setFileName(joFile.getFilename());
            joFileField.setFilePath(joFile.getFilepath());
            JoFileInfo joFileInfo = joFileInfoMapper.getFileInfoById(joFile.getFileid());
            if (joFileInfo != null) {
                joFileField.setFileDes(joFileInfo.getFiledes());
            }
            joFileFieldList.add(joFileField);
        }
        return joFileFieldList;
    }

}
