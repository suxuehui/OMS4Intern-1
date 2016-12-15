package com.arvato.oms.model;

import java.math.BigDecimal;

public class GoodsModel {
    private String goodsno;

    private String goodsname;

    private Integer goodsvnum;

    private BigDecimal goodsprice;

    private Integer goodstolnum;
    private Integer goodsrnum;

    public Integer getGoodsrnum() {
        return goodsrnum;
    }

    public void setGoodsrnum(Integer goodsrnum) {
        this.goodsrnum = goodsrnum;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno == null ? null : goodsno.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getGoodsvnum() {
        return goodsvnum;
    }

    public void setGoodsvnum(Integer goodsvnum) {
        this.goodsvnum = goodsvnum;
    }

    public BigDecimal getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodstolnum() {
        return goodstolnum;
    }

    public void setGoodstolnum(Integer goodstolnum) {
        this.goodstolnum = goodstolnum;
    }
}