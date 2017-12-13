package com.github.my.service;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Subcribe;

/**
 * Created by luohao on 02/12/2017.
 */
public interface SubcribeService {

    int checkVerifyCode(SubReq subReq);

    Subcribe getCurrentVerifyCode(Integer userId);

    Subcribe getCurrentByUserId(Integer userId);

    int insert(Subcribe subcribe);
}
