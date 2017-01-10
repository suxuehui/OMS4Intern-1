package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.ReturnedModel;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 马潇霄 on 2016/12/9.
 */

public interface ReturnedModelService
{
    public int cancelReturn(Integer id,HttpServletRequest request);
    //取消退货单（更新退货单状态为已取消）

    public JSONObject getAllReturnedOrders(int pageNow, int num);
    //分页显示所有退货单

    public JSONObject getGoodsListByRid(String returnedId, int pageNow, int num);
    //根据退货单编码获取商品信息

    public JSONObject getReturnedListBySelect(String select, String value, int pageNow, int num);
    //条件分页模糊查询

    public JSONObject createRefoundOrders(Integer id);
    //批量生成退款单


    public JSONObject createOutbound(Integer id,HttpServletRequest request);
    //换货发货，生成出库单

    public String checkInBound(Integer returnedId, HttpServletRequest request);

    //根据退款单号查询该条退货单记录
    ReturnedModel selectByReturnedId(String returnedId);

    JSONObject getReturnedAndGoodsByid(Integer id);
    //根据id查询退货单和商品信息（用于退货单详情页面）

    String getStatus(Integer id);
    //根据id查询退货单状态

    String getReturnOrChange(Integer id);
    //根据id查询退货单是退货还是换货

    int updateReturnedStateByIid(String inboundId,String state,Date date,String modifyman);
}
