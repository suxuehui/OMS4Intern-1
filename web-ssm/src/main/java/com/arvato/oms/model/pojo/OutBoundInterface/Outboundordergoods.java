package com.arvato.oms.model.pojo.OutBoundInterface;

import java.math.BigDecimal;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class Outboundordergoods {
    private String name;

    private int num;

    private int outboundnum;

    private BigDecimal price;

    private String sku;

    private BigDecimal totalprice;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setNum(int num){
        this.num = num;
    }
    public int getNum(){
        return this.num;
    }
    public void setOutboundnum(int outboundnum){
        this.outboundnum = outboundnum;
    }
    public int getOutboundnum(){
        return this.outboundnum;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setSku(String sku){
        this.sku = sku;
    }
    public String getSku(){
        return this.sku;
    }
    public void setTotalprice(BigDecimal totalprice){
        this.totalprice = totalprice;
    }
    public BigDecimal getTotalprice(){
        return this.totalprice;
    }

}