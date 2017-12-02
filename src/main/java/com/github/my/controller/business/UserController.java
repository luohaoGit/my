package com.github.my.controller.business;

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
    public void relation(RelationReq relationReq){
        userService.relation(relationReq);
    }

    @PostMapping("addr")
    public int addr(UserAddr userAddr){
        addrService.addUserAddr(userAddr);
        return 1;
    }

    @GetMapping("addr/{userId}")
    public UserAddr addrGet(@PathVariable Integer userId){
        return addrService.getUserAddr(userId);
    }

    @PostMapping("check")
    public boolean check(SubReq subReq){
        return subcribeService.checkVerifyCode(subReq);
    }
}
