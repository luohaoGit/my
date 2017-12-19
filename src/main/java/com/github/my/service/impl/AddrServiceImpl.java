package com.github.my.service.impl;

import com.github.my.domain.dto.CommonResp;
import com.github.my.domain.po.Subcribe;
import com.github.my.domain.po.UserAddr;
import com.github.my.mapper.UserAddrMapper;
import com.github.my.service.AddrService;
import com.github.my.service.SubcribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by luohao on 01/12/2017.
 */
@Service
public class AddrServiceImpl implements AddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Autowired
    private SubcribeService subcribeService;

    /**
     * 新增用户地址
     * @param userAddr
     */
    @Override
    public CommonResp addUserAddr(UserAddr userAddr) {
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

        if(userAddr.isCheckFirstSend()) {
            //首次发放暂时放在这里，首先判断本月是否已领取
            Subcribe subcribe = subcribeService.getCurrentByUserId(userId);
            if (subcribe != null) {
                return new CommonResp(0, "保存成功，本月已领取过礼品");
            } else {
                LocalDate now = LocalDate.now();
                int year = now.getYear();
                int month = now.getMonthValue();
                subcribe = new Subcribe();
                subcribe.setUserId(userId);
                subcribe.setMobile(userAddr.getTelephone());
                subcribe.setYear(year + "");
                subcribe.setMonth(month + "");
                subcribe.setHallId(userAddr.getHallId());
                subcribe.setEmployeeId(userAddr.getEmployeeId());
                subcribe.setType(0);
                subcribe.setStatus(1);
                subcribe.setCreateTime(new Date());
                subcribe.setSubTime(new Date());
                subcribe.setDeleted(false);
                subcribeService.insert(subcribe);
                return new CommonResp(0, "保存成功，礼品领取成功");
            }
        }else{
            return new CommonResp(0, "保存成功");
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
