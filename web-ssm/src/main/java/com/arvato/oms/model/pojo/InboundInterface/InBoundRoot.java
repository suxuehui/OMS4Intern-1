package com.arvato.oms.model.pojo.InboundInterface;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
import java.util.List;

public class InBoundRoot {
    private String orderid;

    private String inboundorderid;

    private String returnorderid;

    private List<Inboundordergoods> inboundordergoods ;

    private Inbounddeliveryorder inbounddeliveryorder;

    public void setOrderid(String orderid){
        this.orderid = orderid;
    }
    public String getOrderid(){
        return this.orderid;
    }
    public void setInboundorderid(String inboundorderid){
        this.inboundorderid = inboundorderid;
    }
    public String getInboundorderid(){
        return this.inboundorderid;
    }
    public void setReturnorderid(String returnorderid){
        this.returnorderid = returnorderid;
    }
    public String getReturnorderid(){
        return this.returnorderid;
    }
    public void setInboundordergoods(List<Inboundordergoods> inboundordergoods){
        this.inboundordergoods = inboundordergoods;
    }
    public List<Inboundordergoods> getInboundordergoods(){
        return this.inboundordergoods;
    }
    public void setInbounddeliveryorder(Inbounddeliveryorder inbounddeliveryorder){
        this.inbounddeliveryorder = inbounddeliveryorder;
    }
    public Inbounddeliveryorder getInbounddeliveryorder(){
        return this.inbounddeliveryorder;
    }

}
