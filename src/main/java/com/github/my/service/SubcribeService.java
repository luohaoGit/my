package com.github.my.service;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Subcribe;

/**
 * Created by luohao on 02/12/2017.
 */
public interface SubcribeService {

    boolean checkVerifyCode(SubReq subReq);

    Subcribe getCurrentVerifyCode(Integer userId);

}
