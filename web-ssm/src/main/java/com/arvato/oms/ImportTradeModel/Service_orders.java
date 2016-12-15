package com.arvato.oms.ImportTradeModel;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/14.
 */
public class Service_orders
{
    private List<Service_order> service_order ;


    public void setService_order(List<Service_order> service_order){

        this.service_order = service_order;

    }

    public List<Service_order> getService_order(){

        return this.service_order;

    }
}
