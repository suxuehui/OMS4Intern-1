package com.arvato.oms.model;

import java.math.BigDecimal;

public class RelationogModel {
    private Integer id;

    private String oid;

    private String goodsno;

    private Integer goodnum;

    private byte status;

    private BigDecimal divideorderfee;

    private BigDecimal totalfee;

    public BigDecimal getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(BigDecimal totalfee) {
        this.totalfee = totalfee;
    }

    public BigDecimal getDivideorderfee() {
        return divideorderfee;
    }

    public void setDivideorderfee(BigDecimal divideorderfee) {
        this.divideorderfee = divideorderfee;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

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

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno == null ? null : goodsno.trim();
    }

    public Integer getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(Integer goodnum) {
        this.goodnum = goodnum;
    }
}