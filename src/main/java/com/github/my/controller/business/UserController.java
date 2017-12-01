package com.github.my.controller.business;

import com.github.my.domain.dto.RelationReq;
import com.github.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luohao on 24/09/2017.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("relation")
    public void relation(RelationReq relationReq){
        userService.relation(relationReq);
    }

}
