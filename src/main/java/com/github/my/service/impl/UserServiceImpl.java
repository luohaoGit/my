package com.github.my.service.impl;

import com.github.my.domain.dto.CommonResp;
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
import java.util.logging.Logger;

/**
 * Created by luohao on 24/09/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(getClass().getName());

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
    public CommonResp relation(RelationReq req) {
        String openId = req.getOpenId();
        Integer hallId = req.getHallId();
        User user = userMapper.selectByOpenId(openId);
        logger.info("test------openId:" + openId);
        logger.info("test------hallId:" + hallId);
        if(user != null) {
            Integer userId = user.getId();
            UserHall userHall = userHallMapper.selectByUnique(userId);
            if(userHall == null){
                userHall = new UserHall();
                userHall.setUserId(userId);
                userHall.setHallId(hallId);
                userHallMapper.insert(userHall);
                return new CommonResp(0, "绑定成功");
            }else{
                return new CommonResp(1, "请勿重复绑定");
            }
        }else {
            return new CommonResp(1, "没有找到用户");
        }
    }

    @Override
    public Employee getEmployee(String openId) {
        return employeeMapper.selectByOpenId(openId);
    }
}
