package com.github.my.service.impl;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Subcribe;
import com.github.my.mapper.SubcribeMapper;
import com.github.my.mapper.UserHallMapper;
import com.github.my.service.SubcribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by luohao on 02/12/2017.
 */
@Service
public class SubcribeServiceImpl implements SubcribeService {

    @Autowired
    private SubcribeMapper subcribeMapper;

    @Autowired
    private UserHallMapper userHallMapper;

    @Override
    public boolean checkVerifyCode(SubReq subReq) {
        try {
            String subCode = subReq.getSubCode();
            Integer hallId = subReq.getHallId();
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            List<Subcribe> subcribes
                    = subcribeMapper.selectByYearMonth(year + "", month + "", subCode);
            if(subcribes != null && subcribes.size() > 0){
                Subcribe subcribe = subcribes.stream().filter(s -> {
                    String mobile = s.getMobile();
                    String subTel = mobile.substring(7, 11);
                    return subTel.equals(subReq.getSubTel());
                }).findFirst().orElse(null);

                if(subcribe != null && hallId.equals(subcribe.getHallId())){
                    subcribe.setEmployeeId(subReq.getEmployeeId());
                    subcribe.setHallId(hallId);
                    subcribeMapper.update(subcribe);
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public Subcribe getCurrentVerifyCode(Integer userId) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        return subcribeMapper.selectByUserId(userId, year + "", month + "");
    }

}
