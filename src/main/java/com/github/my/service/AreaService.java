package com.github.my.service;

import com.github.my.domain.po.Area;

import java.util.List;

/**
 * Created by luohao on 19/11/2017.
 */
public interface AreaService {

    Area queryById(Integer id);

    List<Area> queryByPid(Integer pid);

}
