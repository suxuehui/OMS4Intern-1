package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public interface OutboundorderService
{
    //出库单条件查询，
    String outboundsearchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException;

    //出库单子页面查询，
    String listSonPage(HttpServletRequest request);

    //通过oid获取一条信息
    OutboundorderModel selectByOid(String oid);
	
	//向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
    void updateOutboundorder(String orderStatus,String outboundState,String warehouseObid,String expressCompany,String expressId,Date time, String userName,String outboundId );

    void updateOutbound2(String orderStatus,String outboundState, Date time, String userName, String oId);

	//从出库表获取订单号
	String selectOidByOutboundId(String outboundId);

     Model outboundorderdetail(HttpServletRequest request, Model model);
}
