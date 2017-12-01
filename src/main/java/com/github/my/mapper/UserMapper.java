package com.github.my.mapper;

import com.github.my.domain.po.User;

import java.util.List;

public interface UserMapper {

    void updateSubcribeByOpenId(User user);

    User selectByOpenId(String openId);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findByHallId(Integer hallId);
}