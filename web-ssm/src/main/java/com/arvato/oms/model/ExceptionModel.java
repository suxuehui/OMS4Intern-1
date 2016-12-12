package com.arvato.oms.model;

import com.arvato.oms.utils.DatetoString;

import java.util.Date;

public class ExceptionModel {
    private Integer id;

    private String oid;

    private String channeloid;

    private String orderstatus;

    private String orderfrom;

    private String exceptiontype;

    private String expceptioncause;

    private String exceptionstatus;

    private String createtime;

    private String modifytime;

    private String modifyman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getOrderfrom() {
        return orderfrom;
    }

    public void setOrderfrom(String orderfrom) {
        this.orderfrom = orderfrom == null ? null : orderfrom.trim();
    }

    public String getExceptiontype() {
        return exceptiontype;
    }

    public void setExceptiontype(String exceptiontype) {
        this.exceptiontype = exceptiontype == null ? null : exceptiontype.trim();
    }

    public String getExpceptioncause() {
        return expceptioncause;
    }

    public void setExpceptioncause(String expceptioncause) {
        this.expceptioncause = expceptioncause == null ? null : expceptioncause.trim();
    }

    public String getExceptionstatus() {
        return exceptionstatus;
    }

    public void setExceptionstatus(String exceptionstatus) {
        this.exceptionstatus = exceptionstatus == null ? null : exceptionstatus.trim();
    }

    public String getCreatetime() {
        return createtime;
    }
    //日期格式转换
    DatetoString dss=new DatetoString();

    public void setCreatetime(Date createtime) {
        this.createtime = dss.formmat(createtime);
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = dss.formmat(modifytime);
    }

    public String getModifyman() {
        return modifyman;
    }

    public void setModifyman(String modifyman) {
        this.modifyman = modifyman == null ? null : modifyman.trim();
    }
}