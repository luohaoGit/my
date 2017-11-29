package com.github.my.service;

import com.github.my.domain.dto.RelationReq;
import com.github.my.domain.po.User;

/**
 * Created by luohao on 24/09/2017.
 */
public interface UserService {

    void addUser(User user);

    User findByOpenId(String openId);

    void updateUser(User user);

    void updateSubcribe(User user);

    void relation(RelationReq req);
}
