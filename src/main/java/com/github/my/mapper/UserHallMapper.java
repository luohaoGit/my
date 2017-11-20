package com.github.my.mapper;

import com.github.my.domain.po.UserHall;

public interface UserHallMapper {

    UserHall selectByUnique(Integer userId);

    int insert(UserHall userHall);

}