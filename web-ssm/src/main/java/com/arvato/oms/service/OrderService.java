package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.RelationrgModel;
import com.arvato.oms.model.ReturnedModel;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public interface OrderService {
    public JSONObject selectByOid(int pageNo, int pageSize, String oId);
    public OrderModel selectByOid(String oId);
    public List<GoodsPojo> selectGoodsByOid(String oid);
    public JSONObject selects(int queryMode,int pageNo,int pageSize,String oid);
    public int updateByOidSelective(OrderModel record);
    public int previewOrder(String oId,String exceptionType,String name);
    public int cancleOrder(String oId,String uname);
    public int routeOrder(String oId,String uname);
    public int outboundOrder(String oId,String uname);
    public int importOrder(String str);
    public int returnGoods(String jsonStr);
    public int checkreturn(String oid);
	//更新订单列表的订单状态
	void updateOrder(String orderStatus,String oid );
    //根据订单号查询该条退货单记录
    OrderModel selectByoId(String oId);
}
