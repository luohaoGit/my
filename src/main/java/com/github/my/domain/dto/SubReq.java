package com.github.my.domain.dto;

/**
 * Created by luohao on 02/12/2017.
 */
public class SubReq {

    private String subTel;
    private String subCode;
    private Integer userId;

    private Integer employeeId;
    private Integer hallId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getSubTel() {
        return subTel;
    }

    public void setSubTel(String subTel) {
        this.subTel = subTel;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
