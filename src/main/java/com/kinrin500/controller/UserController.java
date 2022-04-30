package com.kinrin500.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kinrin500.entity.User;
import com.kinrin500.service.UserService;
import com.kinrin500.util.Result;
import com.kinrin500.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangtao
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    public UserService userservice;

    @Autowired
    public User user;

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object saveUser(HttpServletRequest request, @RequestBody User user, HttpServletResponse response){
        log.info("[save]:"+user.toString());
        userservice.addUser(user);
        response.setStatus(Response.SC_OK);
        return ResultUtil.result(200,"创建用户成功",JSONObject.toJSON(userservice.findByName(user.getName())));
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Object listUser(){
        List<User> users = userservice.getAllUser();
        log.info("[getAllUer]:{}",users);
        return ResultUtil.result(200,"查询成功", JSONArray.toJSON(users));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Object deleteUser(@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
        log.info("[deleteUser]:{}",request.getRequestURL());
        userservice.deleteUser(id);
        return ResultUtil.result(200,"删除用户成功",null);
    }

}
