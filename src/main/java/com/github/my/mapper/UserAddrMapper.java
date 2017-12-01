package com.github.my.mapper;

import com.github.my.domain.po.UserAddr;

import java.util.List;

public interface UserAddrMapper {

    UserAddr selectByUserId(Integer userId);

    int insert(UserAddr userAddr);

    int update(UserAddr userAddr);

    int delete(Long id);
}