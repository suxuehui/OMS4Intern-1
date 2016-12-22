package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public interface OutboundorderService
{
     public JSONObject outboundOrder(String oId);


    //出库单条件查询，
    String searchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException;

    //出库单子页面查询，
    String listSonPage(HttpServletRequest request);

    //通过oid获取一条信息
    OutboundorderModel selectByOid(String oid);
	
	//向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
    void updateOutboundorder(String orderStatus,String outboundState,String warehouseObid,String expressCompany,String expressId,String outboundId );
	
	//从出库表获取订单号
	String selectOidByOutboundId(String outboundId);
}
