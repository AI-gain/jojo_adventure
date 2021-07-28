package com.jojo.auth.controller;

import com.jojo.auth.bo.User;
import com.jojo.auth.utils.token.Token;
import com.jojo.util.annotaion.PassAuth;
import com.jojo.util.bean.JoMessage;
import com.jojo.util.bean.ResultData;
import com.jojo.util.bean.enmus.JoMessageEnum;
import com.jojo.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/jojo")
@RestController
public class LoginController {

    @Autowired
    RedisUtil redisUtil;

    @PassAuth
    @GetMapping(value = "/authenticate")
    public ResultData authenticate() {
        ResultData resultData = new ResultData();
        User user = new User();
        user.setUserId("11111111111111111");
        user.setUserName("admin");
        String access_token = new Token().getToken();
        String refresh_token = new Token().getToken();
        redisUtil.set(access_token, user, RedisUtil.ACCESS_TOKEN_EXPIRE_TIME);
        redisUtil.set(refresh_token, user.getUserId(), RedisUtil.REFRESH_TOKEN_EXPIRE_TIME);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("access_token", access_token);
        resultMap.put("refresh_token", refresh_token);
        resultMap.put("user", user);
        resultData.setData(resultMap);
        return  resultData;
    }

    @PassAuth
    @GetMapping(value = "/refreshToken")
    public ResultData refreshToken(String refreshToken) {
        ResultData resultData = new ResultData();
        JoMessage joMessage = null;
        try {
            String userId = (String) redisUtil.get(refreshToken);
            User user = new User();
            user.setUserId(userId);
            user.setUserName("refresh");
            String access_token = new Token().getToken();
            redisUtil.set(access_token, user, RedisUtil.ACCESS_TOKEN_EXPIRE_TIME);
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            resultData.setData(map);
            joMessage = new JoMessage(JoMessageEnum.SUCCESS);
        } catch (NullPointerException e) {
            joMessage = new JoMessage(JoMessageEnum.TOKEN_INVALIDATION);
            joMessage.setMsg("登录认证已失效，请重新登录！");
            joMessage.setSuccess(false);

        }
        resultData.setMessage(joMessage);
        return  resultData;
    }
}
