package com.arvato.oms.model.pojo.OutBoundInterface;

import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class OutBoundRoot

{
    private String channelorderid;

    private DeliveryOrder deliveryOrder;

    private String orderid;

    private List<Outboundordergoods> outboundordergoods;

    private String outboundorderid;

    private  String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getChannelorderid()
    {
        return channelorderid;
    }

    public void setChannelorderid(String channelorderid)
    {
        this.channelorderid = channelorderid;
    }

    public DeliveryOrder getDeliveryOrder()
    {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder)
    {
        this.deliveryOrder = deliveryOrder;
    }

    public String getOrderid()
    {
        return orderid;
    }

    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }

    public List<Outboundordergoods> getOutboundordergoods()
    {
        return outboundordergoods;
    }

    public void setOutboundordergoods(List<Outboundordergoods> outboundordergoods)
    {
        this.outboundordergoods = outboundordergoods;
    }

    public String getOutboundorderid()
    {
        return outboundorderid;
    }

    public void setOutboundorderid(String outboundorderid)
    {
        this.outboundorderid = outboundorderid;
    }
}