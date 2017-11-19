package com.github.my.service.impl;

import com.github.my.domain.po.User;
import com.github.my.mapper.UserMapper;
import com.github.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by luohao on 24/09/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setCreatetime(new Date());
        user.setDeleted(false);
        userMapper.insertSelective(user);
    }

    @Override
    public User findByOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateSubcribe(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateSubcribeByOpenId(user);
    }
}
