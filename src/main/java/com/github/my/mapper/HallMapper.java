package com.github.my.mapper;

import com.github.my.domain.po.Hall;

import java.util.List;

public interface HallMapper {

    Hall selectById(Integer id);

    List<Hall> selectByAreaId(Integer areaId);

}