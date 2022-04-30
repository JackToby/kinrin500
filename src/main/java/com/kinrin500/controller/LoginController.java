package com.kinrin500.controller;

import com.alibaba.fastjson.JSONObject;
import com.kinrin500.entity.User;
import com.kinrin500.service.UserService;
import com.kinrin500.util.RedisUtil;
import com.kinrin500.util.Result;
import com.kinrin500.util.ResultUtil;
import com.kinrin500.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping(value = "/dologin")
public class LoginController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    public UserService userservice;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object login(HttpServletRequest request, @RequestBody JSONObject jsonObject, HttpServletResponse response){
        log.info("开始登陆");
        User user=new User();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        user.setName(username);
        user.setPassword(password);
        User findUser = userservice.findByName(username);
        //去数据库拿密码验证用户名密码，这里直接验证
        if(username.equals(findUser.getName())){
            if (!password.equals(findUser.getPassword())){
                return ResultUtil.result(400,"密码错误",null);
            }
        }else{
            return ResultUtil.result(400,"无此用户",null);
        }
        Long currentTimeMillis = System.currentTimeMillis();
        String token= TokenUtil.sign(username,currentTimeMillis);
        redisUtil.set(username,currentTimeMillis,TokenUtil.REFRESH_EXPIRE_TIME);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return ResultUtil.result(200,"登陆成功","Login");
    }



}
