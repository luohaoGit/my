package com.github.my.service.impl;

import com.github.my.domain.po.Area;
import com.github.my.mapper.AreaMapper;
import com.github.my.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 19/11/2017.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Area queryById(Integer id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Area> queryByPid(Integer pid) {
        return areaMapper.selectByPid(pid);
    }
}
