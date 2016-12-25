package com.arvato.oms.model.pojo.InboundInterface;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class Inbounddeliveryorder {
    private String receiver;

    private String receivertel;

    private String receiveraddress;

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setReceivertel(String receivertel){
        this.receivertel = receivertel;
    }
    public String getReceivertel(){
        return this.receivertel;
    }
    public void setReceiveraddress(String receiveraddress){
        this.receiveraddress = receiveraddress;
    }
    public String getReceiveraddress(){
        return this.receiveraddress;
    }

}
