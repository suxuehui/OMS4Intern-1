package com.arvato.oms.model.pojo.OutBoundInterface;

/**
 * Created by 马潇霄 on 2016/12/21.
 */
public class DeliveryOrder {
    private String receiver;

    private String receiveraddress;

    private String receivertel;

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    public String getReceiver(){
        return this.receiver;
    }
    public void setReceiveraddress(String receiveraddress){
        this.receiveraddress = receiveraddress;
    }
    public String getReceiveraddress(){
        return this.receiveraddress;
    }
    public void setReceivertel(String receivertel){
        this.receivertel = receivertel;
    }
    public String getReceivertel(){
        return this.receivertel;
    }

}
