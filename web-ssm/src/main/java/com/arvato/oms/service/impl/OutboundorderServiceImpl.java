package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.dao.OutboundorderModelMapper;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.OutboundorderModel;
import com.arvato.oms.service.OutboundorderService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public class OutboundorderServiceImpl implements OutboundorderService
{
    @Resource
    OrderModelMapper orderModelMapper;
    @Resource
    OutboundorderModelMapper outboundorderModelMapper;
    public JSONObject outboundOrder(String oId) {
        OutboundorderModel outboundorderModel=new OutboundorderModel();
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        outboundorderModel.setOid(orderModel.getOid());
        outboundorderModel.setChanneloid(orderModel.getChanneloid());
        outboundorderModel.setOutboundid("SO"+orderModel.getOid());
        outboundorderModel.setSynchrostate(false);
        outboundorderModel.setReceivername(orderModel.getReceivername());
        outboundorderModel.setReceiveraddress(orderModel.getReceiverprovince()+orderModel.getReceivercity()+orderModel.getReceiverarea()+orderModel.getDetailaddress());
        outboundorderModel.setCreatedtime(new Date());
        int i=outboundorderModelMapper.insert(outboundorderModel);

        return null;
    }
}
