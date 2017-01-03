package com.arvato.oms.service;

import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.model.RefoundOrderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */
public interface RefoundOrderService {

    //分页显示订单列表
    String showRefoundOrderList(HttpServletRequest request);

    //根据退款单号查询该条退款单记录
    RefoundOrderModel selectByDrawbackId(String drawbackId);

    //子页面显示
    String listRefoundOrderSon(HttpServletRequest request);

    //更新退款单状态为已退款
    void updataRefoundDrawbackId(String drawbackStatus,String dataNow,String userName,String drawbackId);
}
