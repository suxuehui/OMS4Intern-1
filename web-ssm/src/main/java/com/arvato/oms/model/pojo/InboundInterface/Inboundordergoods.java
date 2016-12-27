package com.arvato.oms.model.pojo.InboundInterface;

import java.math.BigDecimal;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class Inboundordergoods {
    private String sku;

    private String name;

    private String num;

    private String price;

    private String totalprice;

    private String inboundnum;

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNum()
    {
        return num;
    }

    public void setNum(String num)
    {
        this.num = num;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(String totalprice)
    {
        this.totalprice = totalprice;
    }

    public String getInboundnum()
    {
        return inboundnum;
    }

    public void setInboundnum(String inboundnum)
    {
        this.inboundnum = inboundnum;
    }
}
