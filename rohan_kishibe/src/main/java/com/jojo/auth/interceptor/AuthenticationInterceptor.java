package com.jojo.auth.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jojo.auth.bo.User;
import com.jojo.util.annotaion.PassAuth;
import com.jojo.util.bean.JoMessage;
import com.jojo.util.bean.ResultData;
import com.jojo.util.bean.enmus.JoMessageEnum;
import com.jojo.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    private static Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("user_token");
        String origin = request.getHeader("Origin");
        log.info("request origin => {}", origin);
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            System.out.println("handler instanceof HandlerMethod");
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("method.isAnnotationPresent(PassAuth.class) => {}", method.isAnnotationPresent(PassAuth.class));
        // 跳过认证
        if (method.isAnnotationPresent(PassAuth.class)){
            PassAuth passAuth = method.getAnnotation(PassAuth.class);
            return passAuth.required();
        }

        if (token == null) {
            System.out.println("notokennotokennotokennotokennotoken");
            returnJson(response, JoMessageEnum.TOKEN_MISSING);
            return false;
            // throw new RuntimeException("无认证token, 请先登录");
        }
        User user = (User) redisUtil.get(token);
        if (user == null) {
            returnJson(response, JoMessageEnum.TOKEN_INVALIDATION);
            return false;
        }
        // token续期
        redisUtil.expire(token, RedisUtil.ACCESS_TOKEN_EXPIRE_TIME);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void returnJson(HttpServletResponse response, JoMessageEnum code){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            ResultData result = new ResultData();
            JoMessage joMessage = new JoMessage(code);
            joMessage.setSuccess(false);
            result.setMessage(joMessage);
            result.setData(null);
            writer.print(JSONObject.toJSONString(result));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
