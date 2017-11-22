package com.github.my.service;

import com.github.my.domain.po.Hall;

import java.util.List;

/**
 * Created by luohao on 22/11/2017.
 */
public interface HallService {

    List<Hall> queryByAreaId(Integer areaId);

}
