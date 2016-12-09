package com.arvato.oms.model;

public class RelationRgModel
{
    private Integer id;

    private String returnedid;

    private String goodsno;

    private Integer goodnum;

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