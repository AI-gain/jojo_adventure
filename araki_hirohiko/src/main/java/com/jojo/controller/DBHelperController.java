package com.jojo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jojo.bo.dbbean.TableContent;
import com.jojo.bo.dbbean.TableInfo;
import com.jojo.util.bean.ResultData;
import com.jojo.service.util.IShineYueDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DBHelperController {

    @Autowired
    IShineYueDBService shineYueDBService;

    private static Logger log = LoggerFactory.getLogger(DBHelperController.class);

    @RequestMapping( value="getData", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getData(){
        ResultData result = new ResultData();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result","success");
        result.setData(map);
        return result;
    }

    @RequestMapping( value="getAllTables", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getAllTables(@RequestBody String param){
        System.out.println(param);
        ResultData result = new ResultData();
        JSONObject params = JSON.parseObject(param.toString());
        String owner = params.getString("owner");
        System.out.println(owner);
        if(owner == null || "".equals(owner)){
            return result;
        }
        List<TableInfo> tableInfos = shineYueDBService.getShineYueYun4AllTables(owner);
        result.setData(tableInfos);
        return result;
    }

    @RequestMapping( value="getTableMsg", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getTableMsg(@RequestBody  String param){
        ResultData result = new ResultData();
        JSONObject params = JSONObject.parseObject(param.toString());
        String tableName = params.getString("tableName");
        if(tableName == null || "".equals(tableName)){
            return result;
        }
        List<TableContent> tableContents = shineYueDBService.getTableMsg(tableName);
        result.setData(tableContents);
        return result;
    }
}
