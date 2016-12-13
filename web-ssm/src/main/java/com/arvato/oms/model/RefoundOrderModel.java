package com.arvato.oms.model;

import java.math.BigDecimal;
import java.util.Date;

public class RefoundOrderModel {
    private Integer id;

    private String drawbackid;

    private BigDecimal drawbackmoney;

    private String drawbackstatus;

    private String returnedid;

    private Date createtime;

    private Date modifytime;

    private String modifyman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrawbackid() {
        return drawbackid;
    }

    public void setDrawbackid(String drawbackid) {
        this.drawbackid = drawbackid == null ? null : drawbackid.trim();
    }

    public BigDecimal getDrawbackmoney() {
        return drawbackmoney;
    }

    public void setDrawbackmoney(BigDecimal drawbackmoney) {
        this.drawbackmoney = drawbackmoney;
    }

    public String getDrawbackstatus() {
        return drawbackstatus;
    }

    public void setDrawbackstatus(String drawbackstatus) {
        this.drawbackstatus = drawbackstatus == null ? null : drawbackstatus.trim();
    }

    public String getReturnedid() {
        return returnedid;
    }

    public void setReturnedid(String returnedid) {
        this.returnedid = returnedid == null ? null : returnedid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyman() {
        return modifyman;
    }

    public void setModifyman(String modifyman) {
        this.modifyman = modifyman == null ? null : modifyman.trim();
    }
}