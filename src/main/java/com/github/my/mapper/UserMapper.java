package com.github.my.mapper;

import com.github.my.domain.po.User;
import org.apache.ibatis.annotations.Param;

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

    List<User> findHallNickName(@Param("hallId") Integer hallId, @Param("nickName") String nickName);
}