package com.github.my.service;

import com.github.my.domain.dto.CommonResp;
import com.github.my.domain.po.UserAddr;

/**
 * Created by luohao on 01/12/2017.
 */
public interface AddrService {

    CommonResp addUserAddr(UserAddr userAddr);

    UserAddr getUserAddr(Integer userId);
}
