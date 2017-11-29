package com.github.my.domain.dto;

/**
 * Created by luohao on 28/11/2017.
 */
public class RelationReq {

    private String openId;

    private String type; //C:客户 E:营业员

    private Integer hallId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }
}
