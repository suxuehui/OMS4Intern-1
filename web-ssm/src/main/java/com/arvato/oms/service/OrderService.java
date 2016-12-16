package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.RelationRgModel;
import com.arvato.oms.model.ReturnedModel;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public interface OrderService {
    public JSONObject selectByOid(int pageNo, int pageSize, String oId);
    public JSONObject selectAll(int pageNo, int pageSize);
    public OrderModel selectByOid(String oId);
    public JSONObject selects(int queryMode,int pageNo,int pageSize,String oid);
    public int updateByOidSelective(OrderModel record);
    public int previewOrder(String oId,String exceptionType);
    public int cancleOrder(String oId);
    public int routeOrder(String oId);
    public int outboundOrder(String oId);
    public int insertSelective(ReturnedModel record);
    public int importOrder(String str);
    public int insertSelective(RelationRgModel record);
}
