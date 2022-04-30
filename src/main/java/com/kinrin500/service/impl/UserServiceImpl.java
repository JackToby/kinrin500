package com.kinrin500.service.impl;

import com.kinrin500.entity.User;
import com.kinrin500.mapper.UserMapper;
import com.kinrin500.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangtao
 * @since 2021-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        baseMapper.insert(user);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }
}
