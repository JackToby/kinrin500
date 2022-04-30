package com.kinrin500.service;

import com.kinrin500.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangtao
 * @since 2021-12-23
 */
public interface UserService extends IService<User> {


    public void addUser(User user);

    public User findByName(String username);

    public List<User> getAllUser();

    public void deleteUser(int id);

}
