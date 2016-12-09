package com.arvato.oms.model;

import java.util.Date;

public class InboundorderModel {
    private Integer iid;

    private String oid;

    private String channeloid;

    private String returnedid;

    private String inboundstate;

    private String inboundid;

    private Byte synchrostate;

    private String warehouse;

    private Date createdtime;

    private Date modifytime;

    private String modifyman;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
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

    public String getReturnedid() {
        return returnedid;
    }

    public void setReturnedid(String returnedid) {
        this.returnedid = returnedid == null ? null : returnedid.trim();
    }

    public String getInboundstate() {
        return inboundstate;
    }

    public void setInboundstate(String inboundstate) {
        this.inboundstate = inboundstate == null ? null : inboundstate.trim();
    }

    public String getInboundid() {
        return inboundid;
    }

    public void setInboundid(String inboundid) {
        this.inboundid = inboundid == null ? null : inboundid.trim();
    }

    public Byte getSynchrostate() {
        return synchrostate;
    }

    public void setSynchrostate(Byte synchrostate) {
        this.synchrostate = synchrostate;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
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