package com.github.my.controller.business;

import com.github.my.domain.dto.CommonResp;
import com.github.my.domain.dto.RelationReq;
import com.github.my.domain.dto.SubReq;
import com.github.my.domain.po.UserAddr;
import com.github.my.service.AddrService;
import com.github.my.service.SubcribeService;
import com.github.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by luohao on 24/09/2017.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddrService addrService;

    @Autowired
    private SubcribeService subcribeService;

    @PostMapping("relation")
    public CommonResp relation(RelationReq relationReq){
        return userService.relation(relationReq);
    }

    @PostMapping("addr")
    public CommonResp addr(UserAddr userAddr){
        return addrService.addUserAddr(userAddr);
    }

    @GetMapping("addr/{userId}")
    public UserAddr addrGet(@PathVariable Integer userId){
        return addrService.getUserAddr(userId);
    }

    @PostMapping("check")
    public int check(SubReq subReq){
        return subcribeService.checkVerifyCode(subReq);
    }
}
