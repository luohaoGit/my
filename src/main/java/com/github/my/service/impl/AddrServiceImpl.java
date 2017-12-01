package com.github.my.service.impl;

import com.github.my.domain.po.UserAddr;
import com.github.my.mapper.UserAddrMapper;
import com.github.my.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 01/12/2017.
 */
@Service
public class AddrServiceImpl implements AddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    /**
     * 新增用户地址
     * @param userAddr
     */
    @Override
    public void addUserAddr(UserAddr userAddr) {
        Integer userId = userAddr.getUserId();
        UserAddr oldAddr = userAddrMapper.selectByUserId(userId);
        if(oldAddr != null){
            //更新
            oldAddr.setAddress(userAddr.getAddress());
            oldAddr.setTelephone(userAddr.getTelephone());
            oldAddr.setRecipients(userAddr.getRecipients());
            userAddrMapper.update(oldAddr);
        }else {
            userAddr.setDeleted(false);
            userAddrMapper.insert(userAddr);
        }
    }

    /**
     * 获取用户地址
     * @param userId
     * @return
     */
    @Override
    public UserAddr getUserAddr(Integer userId) {
        return userAddrMapper.selectByUserId(userId);
    }

}
