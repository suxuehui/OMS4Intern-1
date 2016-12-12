package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
public interface ReturnedModelService
{
    public int cancelReturn(List<Integer> ids);
    //取消退货单（更新退货单状态为已取消）

    public JSONObject getAllReturnedOrders(int pageNow, int num);
    //分页显示所有退货单

    public JSONObject getGoodsListByRid(String returnedId,int pageNow, int num);
    //根据退货单编码获取商品信息

    public JSONObject getReturnedListBySelect(String select,String value,int pageNow, int num);
}
