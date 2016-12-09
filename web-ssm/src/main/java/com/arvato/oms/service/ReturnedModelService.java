package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
public interface ReturnedModelService
{
    public int cancelReturn(List<Integer> ids);

    public JSONObject getAllReturnedOrders(int pageNow, int num);

    public JSONObject getGoodsListByRid(String returnedId,int pageNow, int num);
}
