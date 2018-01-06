package com.myz.shirodemo.controller;

import com.myz.shirodemo.common.utils.UUIDUtil;
import com.myz.shirodemo.service.BaseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by myz on 2017/7/4.
 */
@Controller
public class MyController {

    @Autowired
    private BaseService baseService;

    private final Logger logger = LoggerFactory.getLogger(MyController.class);


    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        // 创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            currentUser.login(token);
            // 判断当前用户是否登录
            if (currentUser.isAuthenticated() == true) {
                return "/index.html";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
        return "/loginPage.html";
    }

    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password) {

        boolean result = baseService.registerData(username,password);
        if(result){
            return "/login";
        }
        return "/register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        logger.info("login() 方法被调用");
        return "loginPage.html";
    }

    @RequestMapping(value = "/register")
    public String register() {
        logger.info("register() 方法被调用");
        return "registerPage.html";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        logger.info("hello() 方法被调用");
        return "helloPage.html";
    }
}
