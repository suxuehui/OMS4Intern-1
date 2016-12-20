package com.arvato.oms.model;

import com.arvato.oms.utils.DatetoString;

import java.math.BigDecimal;

import java.util.Date;

public class OrderModel {
    private Integer id;

    private String oid;

    private String channeloid;

    private String orderstatus;

    private String orderform;

    private String buyerid;

    private String ordertime;

    private String basestatus;

    private String paystatus;

    private String paystyle;

    private String paytime;

    private BigDecimal goodstolprice;

    private Double discountprice;

    private BigDecimal ordertolprice;

    private String goodswarehouse;

    private String logisticscompany;

    private String logisticsid;

    private String sendtime;

    private String remark;

    private String receivername;

    private String receivermobel;

    private String receivertelnum;

    private String receiverprovince;

    private String receivercity;

    private String receiverarea;

    private String detailaddress;

    private String zipcode;

    private String modifytime;

    private String modifyman;

    private String  buyeralipayno;

    //日期格式转换
    DatetoString ft=new DatetoString();

    public String getBuyeralipayno() {
        return buyeralipayno;
    }

    public void setBuyeralipayno(String buyeralipayno) {
        this.buyeralipayno = buyeralipayno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getChanneloid() {
        return channeloid;
    }

    public void setChanneloid(String channeloid) {
        this.channeloid = channeloid == null ? null : channeloid.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getOrderform() {
        return orderform;
    }

    public void setOrderform(String orderform) {
        this.orderform = orderform == null ? null : orderform.trim();
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ft.formmat(ordertime);
    }

    public String getBasestatus() {
        return basestatus;
    }

    public void setBasestatus(String basestatus) {
        this.basestatus = basestatus == null ? null : basestatus.trim();
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public String getPaystyle() {
        return paystyle;
    }

    public void setPaystyle(String paystyle) {
        this.paystyle = paystyle == null ? null : paystyle.trim();
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = ft.formmat(paytime);
    }

    public BigDecimal getGoodstolprice() {
        return goodstolprice;
    }

    public void setGoodstolprice(BigDecimal goodstolprice) {
        this.goodstolprice = goodstolprice;
    }

    public Double getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Double discountprice) {
        this.discountprice = discountprice;
    }

    public BigDecimal getOrdertolprice() {
        return ordertolprice;
    }

    public void setOrdertolprice(BigDecimal ordertolprice) {
        this.ordertolprice = ordertolprice;
    }

    public String getGoodswarehouse() {
        return goodswarehouse;
    }

    public void setGoodswarehouse(String goodswarehouse) {
        this.goodswarehouse = goodswarehouse == null ? null : goodswarehouse.trim();
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public String getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(String logisticsid) {
        this.logisticsid = logisticsid == null ? null : logisticsid.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = ft.formmat(sendtime);
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername == null ? null : receivername.trim();
    }

    public String getReceivermobel() {
        return receivermobel;
    }

    public void setReceivermobel(String receivermobel) {
        this.receivermobel = receivermobel == null ? null : receivermobel.trim();
    }

    public String getReceivertelnum() {
        return receivertelnum;
    }

    public void setReceivertelnum(String receivertelnum) {
        this.receivertelnum = receivertelnum == null ? null : receivertelnum.trim();
    }

    public String getReceiverprovince() {
        return receiverprovince;
    }

    public void setReceiverprovince(String receiverprovince) {
        this.receiverprovince = receiverprovince == null ? null : receiverprovince.trim();
    }

    public String getReceivercity() {
        return receivercity;
    }

    public void setReceivercity(String receivercity) {
        this.receivercity = receivercity == null ? null : receivercity.trim();
    }

    public String getReceiverarea() {
        return receiverarea;
    }

    public void setReceiverarea(String receiverarea) {
        this.receiverarea = receiverarea == null ? null : receiverarea.trim();
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress == null ? null : detailaddress.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }


    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = ft.formmat(modifytime);
    }

    public String getModifyman() {
        return modifyman;
    }

    public void setModifyman(String modifyman) {
        this.modifyman = modifyman == null ? null : modifyman.trim();
    }



    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", channeloid='" + channeloid + '\'' +
                ", orderstatus='" + orderstatus + '\'' +
                ", orderform='" + orderform + '\'' +
                ", buyerid='" + buyerid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", basestatus='" + basestatus + '\'' +
                ", paystatus='" + paystatus + '\'' +
                ", paystyle='" + paystyle + '\'' +
                ", paytime='" + paytime + '\'' +
                ", goodstolprice=" + goodstolprice +
                ", discountprice=" + discountprice +
                ", ordertolprice=" + ordertolprice +
                ", goodswarehouse='" + goodswarehouse + '\'' +
                ", logisticscompany='" + logisticscompany + '\'' +
                ", logisticsid='" + logisticsid + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", remark='" + remark + '\'' +
                ", receivername='" + receivername + '\'' +
                ", receivermobel='" + receivermobel + '\'' +
                ", receivertelnum='" + receivertelnum + '\'' +
                ", receiverprovince='" + receiverprovince + '\'' +
                ", receivercity='" + receivercity + '\'' +
                ", receiverarea='" + receiverarea + '\'' +
                ", detailaddress='" + detailaddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", modifytime='" + modifytime + '\'' +
                ", modifyman='" + modifyman + '\'' +
                ", buyeralipayno='" + buyeralipayno + '\'' +
                '}';
    }
}