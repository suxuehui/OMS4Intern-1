package com.arvato.oms.model;

import java.math.BigDecimal;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public class GoodsPojo {
    private String goodsno;



    private String goodsname;

    private Integer goodsvnum;

    private BigDecimal goodsprice;

    private Integer goodstolnum;

    private int goodNum;

    public GoodsPojo() {
    }

    public GoodsPojo(String goodsno, String goodsname, Integer goodsvnum, BigDecimal goodsprice, Integer goodstolnum, Integer goodNum) {
        this.goodsno = goodsno;
        this.goodsname = goodsname;
        this.goodsvnum = goodsvnum;
        this.goodsprice = goodsprice;
        this.goodstolnum = goodstolnum;
        this.goodNum = goodNum;
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

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }
    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }
    @Override
    public String toString() {
        return "GoodsPojo{" +
                "goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodsvnum=" + goodsvnum +
                ", goodsprice=" + goodsprice +
                ", goodstolnum=" + goodstolnum +
                ", goodNum=" + goodNum +
                '}';
    }
}
