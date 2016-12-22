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

    private Integer goodsrnum;

    private Integer goodstolnum;

    private int goodNum;
	
	private Integer goodsnum;

    private BigDecimal divideorderfee;

    private BigDecimal totalfee;

    public GoodsPojo() {
    }

    public GoodsPojo(String goodsno, String goodsname, Integer goodsvnum, BigDecimal goodsprice, Integer goodstolnum, int goodNum, BigDecimal divideorderfee, BigDecimal totalfee) {
        this.goodsno = goodsno;
        this.goodsname = goodsname;
        this.goodsvnum = goodsvnum;
        this.goodsprice = goodsprice;
        this.goodstolnum = goodstolnum;
        this.goodNum = goodNum;
        this.divideorderfee = divideorderfee;
        this.totalfee = totalfee;
    }

    public BigDecimal getDivideorderfee() {
        return divideorderfee;
    }

    public void setDivideorderfee(BigDecimal divideorderfee) {
        this.divideorderfee = divideorderfee;
    }

    public BigDecimal getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(BigDecimal totalfee) {
        this.totalfee = totalfee;
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

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
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

    public Integer getGoodsrnum() {
        return goodsrnum;
    }

    public void setGoodsrnum(Integer goodsrnum) {
        this.goodsrnum = goodsrnum;
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

    @Override
    public String toString() {
        return "GoodsPojo{" +
                "goodsno='" + goodsno + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodsvnum=" + goodsvnum +
                ", goodsprice=" + goodsprice +
                ", goodstolnum=" + goodstolnum +
                ", goodNum=" + goodNum +
                ", divideorderfee=" + divideorderfee +
                ", totalfee=" + totalfee +
                '}';
    }
}
