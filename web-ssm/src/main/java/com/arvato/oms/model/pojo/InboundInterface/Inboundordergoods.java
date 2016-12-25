package com.arvato.oms.model.pojo.InboundInterface;

import java.math.BigDecimal;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class Inboundordergoods {
    private String sku;

    private String name;

    private int num;

    private BigDecimal price;

    private BigDecimal totalprice;

    private int inboundnum;

    public void setSku(String sku){
        this.sku = sku;
    }
    public String getSku(){
        return this.sku;
    }
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
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setTotalprice(BigDecimal totalprice){
        this.totalprice = totalprice;
    }
    public BigDecimal getTotalprice(){
        return this.totalprice;
    }
    public void setInboundnum(int inboundnum){
        this.inboundnum = inboundnum;
    }
    public int getInboundnum(){
        return this.inboundnum;
    }

}
