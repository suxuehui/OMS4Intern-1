package com.arvato.oms.model;

import com.arvato.oms.utils.DatetoString;

import java.math.BigDecimal;
import java.util.Date;

public class RefoundOrderModel {
    private Integer id;

    private String drawbackid;

    private BigDecimal drawbackmoney;

    private String drawbackstatus;

    private String returnedid;

    private String createtime;

    private String modifytime;

    private String modifyman;
    //日期格式转换
    DatetoString ft=new DatetoString();

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = ft.formmat(createtime);
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = ft.formmat(modifytime);
    }

    public String getModifyman() {
        return modifyman;
    }

    public void setModifyman(String modifyman) {
        this.modifyman = modifyman == null ? null : modifyman.trim();
    }
}