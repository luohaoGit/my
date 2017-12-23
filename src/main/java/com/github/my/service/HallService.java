package com.github.my.service;

import com.github.my.domain.po.Hall;
import com.github.my.domain.po.User;

import java.util.List;

/**
 * Created by luohao on 22/11/2017.
 */
public interface HallService {

    List<Hall> queryByAreaId(Integer areaId);

    Hall queryByEmployeeId(String openId);

    List<User> queryUsersByHallId(Integer hallId);

    List<User> queryUsersByHallIdNick(Integer hallId, String nickName);

    Hall queryByUserOpenId(String openId);
}
