package com.arvato.oms.model.pojo;

import java.math.BigDecimal;

public class GoodsAndStatus
{
    private String goodsno;

    private String goodsname;

    private Integer goodsvnum;

    private BigDecimal goodsprice;

    private Integer goodstolnum;

    private int booknum;


    public String getGoodsno()
    {
        return goodsno;
    }

    public void setGoodsno(String goodsno)
    {
        this.goodsno = goodsno;
    }

    public String getGoodsname()
    {
        return goodsname;
    }

    public void setGoodsname(String goodsname)
    {
        this.goodsname = goodsname;
    }

    public Integer getGoodsvnum()
    {
        return goodsvnum;
    }

    public void setGoodsvnum(Integer goodsvnum)
    {
        this.goodsvnum = goodsvnum;
    }

    public BigDecimal getGoodsprice()
    {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice)
    {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodstolnum()
    {
        return goodstolnum;
    }

    public void setGoodstolnum(Integer goodstolnum)
    {
        this.goodstolnum = goodstolnum;
    }

    public int getBooknum()
    {
        return booknum;
    }

    public void setBooknum(int booknum)
    {
        this.booknum = booknum;
    }
}