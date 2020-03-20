package com.test.springboot.controller;

import com.test.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

//    // 浏览器和客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handlerException(Exception e){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("code", "not.exist");
//        map.put("message", e.getMessage());
//        return map;
//    }


    // 页面请求 返回错误页面
    // 非页面请求 返回json数据
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        // 设置自己的错误状态码 4xx 5xx
        // 否则就不会进入定制错误页面的解析流程
        /**
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "not.exist");
        map.put("message", e.getMessage());

        // 将数据放到请求域中
        request.setAttribute("ext", map);
        // 转发的error请求
        return "forward:/error";
    }
}
