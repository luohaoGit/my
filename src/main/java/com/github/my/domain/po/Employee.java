package com.github.my.domain.po;

/**
 * Created by luohao on 22/11/2017.
 */
public class Employee {

    private Integer id;

    private String name;

    private Integer hallId;

    private String workNumber;

    private String openId;

    private String businessUrl;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
    }
}
