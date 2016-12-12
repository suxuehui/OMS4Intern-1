package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.OrderModel;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public interface OrderService {
    public JSONObject selectAll(int pageNo, int pageSize);
    public OrderModel selectByOid(String oId);
    public JSONObject selects(int queryMode,int pageNo,int pageSize,String oid);
    public int updateByOidSelective(OrderModel record);
    public int previewOrder(String oId,String exceptionType);
}
