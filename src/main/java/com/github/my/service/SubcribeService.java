package com.github.my.service;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Subcribe;

import java.util.List;
import java.util.Map;

/**
 * Created by luohao on 02/12/2017.
 */
public interface SubcribeService {

    int checkVerifyCode(SubReq subReq);

    Subcribe getCurrentVerifyCode(Integer userId);

    Subcribe getCurrentByUserId(Integer userId);

    int insert(Subcribe subcribe);

    List<Subcribe> getByHall(Integer hallId);

    Map<String, Integer> getReport(String empOpenId);
}
