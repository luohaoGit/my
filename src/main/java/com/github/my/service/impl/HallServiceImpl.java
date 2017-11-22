package com.github.my.service.impl;

import com.github.my.domain.po.Hall;
import com.github.my.mapper.HallMapper;
import com.github.my.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 22/11/2017.
 */
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallMapper hallMapper;

    @Override
    public List<Hall> queryByAreaId(Integer areaId) {
        return hallMapper.selectByAreaId(areaId);
    }

}
