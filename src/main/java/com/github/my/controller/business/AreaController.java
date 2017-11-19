package com.github.my.controller.business;

import com.github.my.domain.po.Area;
import com.github.my.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by luohao on 19/11/2017.
 */
@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("{id}")
    public Area getById(@PathVariable Integer id){
        return areaService.queryById(id);
    }

    @RequestMapping("pid/{pid}")
    public List<Area> getByPid(@PathVariable Integer pid){
        return areaService.queryByPid(pid);
    }

}
