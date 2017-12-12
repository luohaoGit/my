package com.github.my.service.impl;

import com.github.my.domain.dto.RelationReq;
import com.github.my.domain.po.Employee;
import com.github.my.domain.po.User;
import com.github.my.domain.po.UserHall;
import com.github.my.mapper.EmployeeMapper;
import com.github.my.mapper.UserHallMapper;
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

    @Autowired
    private UserHallMapper userHallMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

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

    @Override
    public void relation(RelationReq req) {
        String openId = req.getOpenId();
        Integer hallId = req.getHallId();
        User user = userMapper.selectByOpenId(openId);
        if(user != null) {
            Integer userId = user.getId();
            UserHall userHall = userHallMapper.selectByUnique(userId);
            if(userHall != null){
                userHall.setHallId(hallId);
                userHallMapper.updateHall(userHall);
            }else{
                userHall = new UserHall();
                userHall.setUserId(userId);
                userHall.setHallId(hallId);
                userHallMapper.insert(userHall);
            }
        }
    }

    @Override
    public Employee getEmployee(String openId) {
        return employeeMapper.selectByOpenId(openId);
    }
}
