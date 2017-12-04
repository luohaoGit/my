package com.github.my.service.impl;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Subcribe;
import com.github.my.domain.po.UserHall;
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

    /**
     * -1：异常， 0：已发放， 1：成功， 2：用户不在所绑定的营业厅
     * @param subReq
     * @return
     */
    @Override
    public int checkVerifyCode(SubReq subReq) {
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
                    return subTel.equals(subReq.getSubTel()) && 0 == s.getStatus();
                }).findFirst().orElse(null);

                if(subcribe != null){
                    Integer userId = subcribe.getUserId();
                    UserHall userHall = userHallMapper.selectByUnique(userId);
                    if(userHall != null && hallId.equals(userHall.getHallId())) {
                        subcribe.setEmployeeId(subReq.getEmployeeId());
                        subcribe.setHallId(hallId);
                        subcribeMapper.update(subcribe);
                        return 1;
                    }else{
                        return 2;
                    }
                }else{
                    return 0;
                }
            }
        }catch (Exception e){
            return -1;
        }
        return -1;
    }

    @Override
    public Subcribe getCurrentVerifyCode(Integer userId) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        return subcribeMapper.selectByUserId(userId, year + "", month + "");
    }

}
