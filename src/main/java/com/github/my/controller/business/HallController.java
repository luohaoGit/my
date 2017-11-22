package com.github.my.controller.business;

import com.github.my.domain.po.Hall;
import com.github.my.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by luohao on 22/11/2017.
 */
@RestController
@RequestMapping("hall")
public class HallController {

    @Autowired
    private HallService hallService;

    @RequestMapping("areaId/{areaId}")
    public List<Hall> getById(@PathVariable Integer areaId){
        return hallService.queryByAreaId(areaId);
    }

}
