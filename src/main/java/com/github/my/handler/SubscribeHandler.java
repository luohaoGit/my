package com.github.my.handler;

import com.github.my.builder.TextBuilder;
import com.github.my.domain.po.User;
import com.github.my.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class SubscribeHandler extends AbstractHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {
            String openId = userWxInfo.getOpenId();

            User oldUser = userService.findByOpenId(openId);
            if(oldUser != null){
                oldUser.setSubcribe(true);
                oldUser.setSubscribetime(userWxInfo.getSubscribeTime());
                userService.updateSubcribe(oldUser);
            }else {
                User user = new User();
                user.setSubcribe(true);
                user.setOpenid(openId);
                user.setWxnickname(userWxInfo.getNickname());
                user.setSex(userWxInfo.getSex());
                user.setWxlanguage(userWxInfo.getLanguage());
                user.setCity(userWxInfo.getCity());
                user.setProvince(userWxInfo.getProvince());
                user.setCountry(userWxInfo.getCountry());
                user.setHeadimgurl(userWxInfo.getHeadImgUrl());
                user.setSubscribetime(userWxInfo.getSubscribeTime());
                user.setUnionid(userWxInfo.getUnionId());
                user.setRemark(userWxInfo.getRemark());
                user.setGroupid(userWxInfo.getGroupId());
                user.setRegistertime(new Date());

                logger.info("新用户:" + user);

                userService.addUser(user);
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
