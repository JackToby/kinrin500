package com.kinrin500.mapper;

import com.kinrin500.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangtao
 * @since 2021-12-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    User findByName(String username);

    List<User> getAllUser();

    public void deleteUser(int id);

}
