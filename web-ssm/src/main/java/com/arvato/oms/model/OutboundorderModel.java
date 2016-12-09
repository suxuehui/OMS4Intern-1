package com.arvato.oms.model;

import java.util.Date;

public class OutboundorderModel {
    private Integer cid;

    private String oid;

    private String channeloid;

    private String orderstatus;

    private String warehouseobid;

    private String outboundid;

    private String outboundstate;

    private Byte synchrostate;

    private String receivername;

    private String expresscompany;

    private String expressid;

    private String receiveraddress;

    private Date createdtime;

    private Date modifytime;

    private String modifyman;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getWarehouseobid() {
        return warehouseobid;
    }

    public void setWarehouseobid(String warehouseobid) {
        this.warehouseobid = warehouseobid == null ? null : warehouseobid.trim();
    }

    public String getOutboundid() {
        return outboundid;
    }

    public void setOutboundid(String outboundid) {
        this.outboundid = outboundid == null ? null : outboundid.trim();
    }

    public String getOutboundstate() {
        return outboundstate;
    }

    public void setOutboundstate(String outboundstate) {
        this.outboundstate = outboundstate == null ? null : outboundstate.trim();
    }

    public Byte getSynchrostate() {
        return synchrostate;
    }

    public void setSynchrostate(Byte synchrostate) {
        this.synchrostate = synchrostate;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername == null ? null : receivername.trim();
    }

    public String getExpresscompany() {
        return expresscompany;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany == null ? null : expresscompany.trim();
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid == null ? null : expressid.trim();
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress == null ? null : receiveraddress.trim();
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