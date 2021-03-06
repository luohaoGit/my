package com.github.my.service.impl;

import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.Employee;
import com.github.my.domain.po.Subcribe;
import com.github.my.domain.po.UserHall;
import com.github.my.mapper.EmployeeMapper;
import com.github.my.mapper.SubcribeMapper;
import com.github.my.mapper.UserHallMapper;
import com.github.my.service.SubcribeService;
import com.github.my.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luohao on 02/12/2017.
 */
@Service
public class SubcribeServiceImpl implements SubcribeService {

    @Autowired
    private SubcribeMapper subcribeMapper;

    @Autowired
    private UserHallMapper userHallMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

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
        return subcribeMapper.selectByUserId(userId, year + "", month + "", 0);
    }

    @Override
    public Subcribe getCurrentByUserId(Integer userId) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        return subcribeMapper.selectByUserId(userId, year + "", month + "", null);
    }

    @Override
    public int insert(Subcribe subcribe) {
        return subcribeMapper.insert(subcribe);
    }

    /**
     * 查找营业厅所有发放
     * @param hallId
     * @return
     */
    @Override
    public List<Subcribe> getSentByHall(Integer hallId) {
        return subcribeMapper.selectSentByHallId(hallId);
    }

    @Override
    public Map<String, Integer> getReport(String empOpenId) {
        Map<String, Integer> map = new HashMap<>();
        Employee employee = employeeMapper.selectByOpenId(empOpenId);
        if(employee != null){
            Integer hallId = employee.getHallId();
            List<Subcribe> subcribes = getSentByHall(hallId);
            if(subcribes != null && subcribes.size() > 0){
                map.put("all", subcribes.size());
                Long thisMonth = subcribes.stream().filter(s -> {
                    Date subTime = s.getSubTime();
                    return subTime != null && DateUtil.isThisMonth(subTime.getTime());
                }).count();

                Long today = subcribes.stream().filter(s -> {
                    Date subTime = s.getSubTime();
                    return subTime != null && DateUtil.isToday(subTime.getTime());
                }).count();

                map.put("month", thisMonth.intValue());
                map.put("today", today.intValue());
            }
        }
        return map;
    }


}
