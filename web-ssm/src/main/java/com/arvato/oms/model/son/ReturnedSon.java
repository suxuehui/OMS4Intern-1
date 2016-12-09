package com.arvato.oms.model.son;

import java.math.BigDecimal;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
public class ReturnedSon
{
    private String goodsno;

    private String goodsname;

    private Integer goodnum;

    private BigDecimal goodsprice;

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

    public Integer getGoodnum()
    {
        return goodnum;
    }

    public void setGoodnum(Integer goodnum)
    {
        this.goodnum = goodnum;
    }

    public BigDecimal getGoodsprice()
    {
        return goodsprice;
    }

    public void setGoodsprice(BigDecimal goodsprice)
    {
        this.goodsprice = goodsprice;
    }
}
