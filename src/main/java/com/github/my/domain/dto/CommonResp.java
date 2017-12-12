package com.github.my.domain.dto;

/**
 * Created by luohao on 12/12/2017.
 */
public class CommonResp {

    private int code; //0:成功 1:失败
    private String msg;

    public CommonResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
