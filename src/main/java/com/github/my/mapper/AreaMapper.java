package com.github.my.mapper;

import com.github.my.domain.po.Area;

import java.util.List;

public interface AreaMapper {

    List<Area> selectByPid(Integer pid);

    Area selectByPrimaryKey(Integer id);

}