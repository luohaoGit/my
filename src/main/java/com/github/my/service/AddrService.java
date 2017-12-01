package com.github.my.service;

import com.github.my.domain.po.UserAddr;

/**
 * Created by luohao on 01/12/2017.
 */
public interface AddrService {

    void addUserAddr(UserAddr userAddr);

    UserAddr getUserAddr(Integer userId);
}
