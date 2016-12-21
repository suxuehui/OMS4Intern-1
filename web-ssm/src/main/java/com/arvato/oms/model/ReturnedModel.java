package com.arvato.oms.model;

import com.arvato.oms.utils.DatetoString;

import java.math.BigDecimal;
import java.util.Date;

public class ReturnedModel {
    private Integer id;

    private String returnedid;

    private String returnedorchange;

    private String returnedstatus;

    private String oid;

    private String channeloid;

    private BigDecimal returnedmoney;

    private String createtime;

    private String modifytime;

    private String modifyman;

    private String buyeralipayno;
//日期转换
    DatetoString ft=new DatetoString();

    public String getBuyeralipayno() {
        return buyeralipayno;
    }

    public void setBuyeralipayno(String buyeralipayno) {
        this.buyeralipayno = buyeralipayno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReturnedid() {
        return returnedid;
    }

    public void setReturnedid(String returnedid) {
        this.returnedid = returnedid == null ? null : returnedid.trim();
    }

    public String getReturnedorchange() {
        return returnedorchange;
    }

    public void setReturnedorchange(String returnedorchange) {
        this.returnedorchange = returnedorchange == null ? null : returnedorchange.trim();
    }

    public String getReturnedstatus() {
        return returnedstatus;
    }

    public void setReturnedstatus(String returnedstatus) {
        this.returnedstatus = returnedstatus == null ? null : returnedstatus.trim();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getChanneloid() {
        return channeloid;
    }

    public void setChanneloid(String channeloid) {
        this.channeloid = channeloid == null ? null : channeloid.trim();
    }

    public BigDecimal getReturnedmoney() {
        return returnedmoney;
    }

    public void setReturnedmoney(BigDecimal returnedmoney) {
        this.returnedmoney = returnedmoney;
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