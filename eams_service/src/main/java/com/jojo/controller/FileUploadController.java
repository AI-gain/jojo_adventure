package com.jojo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jojo.bo.JoFileField;
import com.jojo.po.JoFile;
import com.jojo.service.file.IFileManagerService;
import com.jojo.util.annotaion.PassAuth;
import com.jojo.util.bean.JoMessage;
import com.jojo.util.bean.ResultData;
import com.jojo.util.bean.enmus.JoMessageEnum;
import com.jojo.utils.fdfs.FastDFSClient;
import com.jojo.utils.fdfs.FastDFSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("jojo")
@RestController
public class FileUploadController {

    @Autowired
    private IFileManagerService fileManagerService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Value("${file.uploadpath}")
    private String filePath;

    @Value("${file.fdfs.http_secret_key}")
    private String httpSecretKey;

    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping( value="file/category", method = RequestMethod.GET)
    @ResponseBody
    public ResultData getFileCategory(){
        ResultData result = new ResultData();
        JoMessage message = new JoMessage();
        List<Map<String, Object>> categoryList = fileManagerService.getFileCategories();
        result.setData(categoryList);
        result.setMessage(message);
        return result;
    }

    @PassAuth
    @PostMapping(value = "upload/fdfs")
    @ResponseBody
    public ResultData fileUploadFdfs(@RequestParam("file") MultipartFile file, String params) {
        ResultData resultData = new ResultData();
        JoMessage message = null;
        JSONObject jsonObject = JSONObject.parseObject(params);

        if (file.isEmpty()) {
            message = new JoMessage(JoMessageEnum.ERROR);
            message.setMsg("上传失败，请选择文件!");
            message.setSuccess(false);
            resultData.setMessage(message);
            return resultData;
        }
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        log.info("filename ==> {}, fileType ==> {}", filename, fileType);
        Long fileSize = file.getSize();
        log.info("params ===> {}", jsonObject);
        String fileDesc = jsonObject.getString("fileDes");
        String fileInfo = jsonObject.getString("fileInfo");
        String fileCategory = jsonObject.getString("fileCategory");
        JoFileField newfile = new JoFileField();
        newfile.setFileName(filename);
        newfile.setFileSize(fileSize);
        newfile.setFileType(fileType);
        newfile.setCreateUser(0L);
        newfile.setFileDes(fileDesc);
        newfile.setFileInfo(fileInfo);
        newfile.setFileCategory(fileCategory);
        // 上传到服务器
        try {
            String filepath = fastDFSClient.uploadFileWithMultipart(file);
            // String token = FastDFSClient.getToken(filepath, httpSecretKey);
            newfile.setFilePath(filepath);
            newfile.setCreateDate(new Date());
            fileManagerService.saveFile(newfile);
            message = new JoMessage(JoMessageEnum.SUCCESS);
            message.setMsg("上传成功！");
            System.out.println(filepath);
        } catch (FastDFSException e) {
            message = new JoMessage(JoMessageEnum.ERROR);
            message.setMsg("上传失败！" + e.toString());
            message.setSuccess(false);
            e.printStackTrace();
        }
        resultData.setMessage(message);
        return resultData;
    }

    @RequestMapping( value="upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultData fileUpload(@RequestParam("file") MultipartFile file, String params){
        ResultData result = new ResultData();
        JoMessage message = null;
        JSONObject jsonObject = JSONObject.parseObject(params);

        if (file.isEmpty()) {
            message = new JoMessage(JoMessageEnum.ERROR);
            message.setMsg("上传失败，请选择文件!");
            message.setSuccess(false);
            result.setMessage(message);
            return result;
        }

        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        log.info("filename ==> {}, fileType ==> {}", filename, fileType);
        Long fileSize = file.getSize();
        log.info("params ===> {}", jsonObject);
        String fileDesc = jsonObject.getString("fileDes");
        String fileInfo = jsonObject.getString("fileInfo");
        String fileCategory = jsonObject.getString("fileCategory");
        JoFileField newfile = new JoFileField();
        newfile.setFileName(filename);
        newfile.setFileSize(fileSize);
        newfile.setFileType(fileType);
        newfile.setCreateUser(0L);
        newfile.setFileDes(fileDesc);
        newfile.setFileInfo(fileInfo);
        newfile.setFileCategory(fileCategory);
        try {
            File parentFile = new File(filePath + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\\" + System.currentTimeMillis());
            File dest = new File(parentFile.getAbsolutePath());
            if (!dest.exists()){
                dest.mkdirs();     //判断父路径是否存在，不存在就创建出来
            }
            log.info("上传文件路径：{}", dest.getAbsolutePath());
            File uploadFile = new File(dest, filename);
            file.transferTo(uploadFile);
            newfile.setFilePath(dest.getPath() + "\\" + filename);
            newfile.setCreateDate(new Date());
            fileManagerService.saveFile(newfile);
            message = new JoMessage(JoMessageEnum.SUCCESS);
            message.setMsg("上传成功！");
        } catch (IOException e) {
            message = new JoMessage(JoMessageEnum.ERROR);
            message.setMsg("上传失败！" + e.toString());
            message.setSuccess(false);
            e.printStackTrace();
        }
        result.setMessage(message);
        return result;
    }

    @PassAuth
    @GetMapping(value = "allFiles")
    @ResponseBody
    public ResultData getAllFiles() {
        ResultData resultData = new ResultData();
        List<JoFileField> joFileFieldList = fileManagerService.getAllFiles();
        resultData.setData(joFileFieldList);
        return resultData;
    }

    @GetMapping("/downloadFile/{fileId}")
    @ResponseBody
    public String fileDownLoad(@PathVariable("fileId") Long fileId, HttpServletResponse response) throws UnsupportedEncodingException {
        JoFileField joFileField = fileManagerService.getFileInfo(fileId);
        File file = new File(joFileField.getFilePath());
        //判断文件父目录是否存在
        if(file.exists()){
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(joFileField.getFileName(),"UTF-8"));
            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            //输出流
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
