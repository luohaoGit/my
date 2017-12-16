package com.github.my.handler;

import com.github.my.domain.po.Hall;
import com.github.my.domain.po.Subcribe;
import com.github.my.domain.po.User;
import com.github.my.domain.po.UserHall;
import com.github.my.mapper.HallMapper;
import com.github.my.mapper.UserHallMapper;
import com.github.my.service.SubcribeService;
import com.github.my.service.UserService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;

@Component
public class MenuHandler extends AbstractHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private SubcribeService subcribeService;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private UserHallMapper userHallMapper;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {

    String msg = String.format("type:%s, event:%s, key:%s",
        wxMessage.getMsgType(), wxMessage.getEvent(),
        wxMessage.getEventKey());

    if (MenuButtonType.VIEW.equalsIgnoreCase(wxMessage.getEvent())) {
      return null;
    } else if(MenuButtonType.CLICK.equalsIgnoreCase(wxMessage.getEvent())){
        String eventKey = wxMessage.getEventKey();
        if("VERIFY_CODE".equals(eventKey)){
            msg = "没有可用图书码";
            String openId = wxMessage.getFromUser();
            User user = userService.findByOpenId(openId);
            if(user != null){
                Integer userId = user.getId();
                UserHall userHall = userHallMapper.selectByUnique(userId);
                if(userHall != null){
                    Integer hallId = userHall.getHallId();
                    Hall hall = hallMapper.selectById(hallId);
                    if(hall != null && 2 == hall.getType()){
                        msg = "您好，“扬子作文学堂”的订购客户，您获赠的图书通过邮寄方式送达您登记的地址。";
                    }else{
                        Subcribe subcribe = subcribeService.getCurrentVerifyCode(userId);
                        if(subcribe != null && !"".equals(subcribe.getVerifyCode())){
                            msg = "您本月的图书码为:" + subcribe.getVerifyCode();
                        }
                    }
                }
            }
        }
    }

    return WxMpXmlOutMessage.TEXT().content(msg)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();
  }

}
