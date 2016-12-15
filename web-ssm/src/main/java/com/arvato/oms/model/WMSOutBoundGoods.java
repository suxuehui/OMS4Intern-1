package com.arvato.oms.model;

import java.math.BigDecimal;

/**
 * Created by ZHAN545 on 2016/12/13.
 */
public class WMSOutBoundGoods {
    private String sku;
    private String name;
    private int num;
    private BigDecimal price;
    private BigDecimal totalprice;
    private int outboundnum;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public int getOutboundnum() {
        return outboundnum;
    }

    public void setOutboundnum(int outboundnum) {
        this.outboundnum = outboundnum;
    }
}
