package com.github.my.controller.business;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by luohao on 19/11/2017.
 */
@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    private WxMpService wxService;

    @RequestMapping(value = "/", produces = "text/html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "activity", produces = "text/html")
    public ModelAndView activity(@RequestParam String code) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        map.put("openId", wxMpOAuth2AccessToken.getOpenId());
        return new ModelAndView("active", map);
    }

    @RequestMapping(value = "detail", produces = "text/html")
    public ModelAndView detail(@RequestParam String code) throws Exception{
        //WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        //todo 判断是否为营业员
        HashMap<String, Object> map = new HashMap<>();
        //String openId = wxMpOAuth2AccessToken.getOpenId();
        //map.put("openId", openId);
        return new ModelAndView("detail", map);
    }
}
