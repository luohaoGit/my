package com.github.my.service.impl;

import com.github.my.domain.po.Employee;
import com.github.my.domain.po.Hall;
import com.github.my.domain.po.User;
import com.github.my.mapper.EmployeeMapper;
import com.github.my.mapper.HallMapper;
import com.github.my.mapper.UserMapper;
import com.github.my.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 22/11/2017.
 */
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Hall> queryByAreaId(Integer areaId) {
        return hallMapper.selectByAreaId(areaId);
    }

    /**
     * 获取营业员的厅
     * @param openId
     * @return
     */
    @Override
    public Hall queryByEmployeeId(String openId) {
        Employee employee = employeeMapper.selectByOpenId(openId);
        if(employee != null){
            Integer hallId = employee.getHallId();
            return hallMapper.selectById(hallId);
        }
        return null;
    }

    /**
     * 获取营业厅的用户
     * @param hallId
     * @return
     */
    @Override
    public List<User> queryUsersByHallId(Integer hallId) {
        return userMapper.findByHallId(hallId);
    }

}
