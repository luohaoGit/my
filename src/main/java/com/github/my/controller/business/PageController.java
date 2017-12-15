package com.github.my.controller.business;

import com.alibaba.fastjson.JSON;
import com.github.my.domain.po.Employee;
import com.github.my.domain.po.Hall;
import com.github.my.service.HallService;
import com.github.my.service.UserService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by luohao on 19/11/2017.
 */
@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private UserService userService;

    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/", produces = "text/html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "activity", produces = "text/html")
    public ModelAndView activity(@RequestParam String code) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        map.put("openId", openId);

        Hall hall = hallService.queryByUserOpenId(openId);
        map.put("hall", JSON.toJSONString(hall));

        return new ModelAndView("active", map);
    }

    @RequestMapping(value = "detail", produces = "text/html")
    public ModelAndView detail(@RequestParam String code) throws Exception{
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        HashMap<String, Object> map = new HashMap<>();
        String openId = wxMpOAuth2AccessToken.getOpenId();
        map.put("openId", openId);

        Employee employee = userService.getEmployee(openId);
        map.put("employee", JSON.toJSONString(employee));

        Hall hall = hallService.queryByEmployeeId(openId);
        map.put("hall", JSON.toJSONString(hall));

        return new ModelAndView("detail", map);
    }

    @RequestMapping(value = "business", produces = "text/html")
    public void business(@RequestParam String code, HttpServletResponse response) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        map.put("openId", openId);

        Employee employee = userService.getEmployee(openId);
        map.put("employee", JSON.toJSONString(employee));

        response.sendRedirect(employee.getBusinessUrl());
    }

    @RequestMapping(value = "table", produces = "text/html")
    public ModelAndView table(@RequestParam String code) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        map.put("openId", openId);

        return new ModelAndView("table", map);
    }
}
