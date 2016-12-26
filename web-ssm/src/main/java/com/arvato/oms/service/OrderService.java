package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.OutboundorderModel;

import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public interface OrderService {
    //根据订单号分页查询商品信息
    public JSONObject selectByOid(int pageNo, int pageSize, String oId);
    //根据订单号精确查询订单
    public OrderModel selectByOid(String oId);
    //根据订单号查询所有商品信息
    public List<GoodsPojo> selectGoodsByOid(String oid);
    //分条件查询，分页，模糊查询
    public JSONObject selects(int queryMode,int pageNo,int pageSize,String oid);
    //更新订单
    public int updateByOidSelective(OrderModel record);
    //预检订单
    public int previewOrder(String oId,String exceptionType,String name);
    //取消订单
    public int cancleOrder(String oId,String uname);
    //路由
    public int routeOrder(String oId,String uname);
    //生成出库单
    public int outboundOrder(String oId,String uname);
    //调用WMS接口发送出库单
    public int sendOutboundOrder(OrderModel orderModel, OutboundorderModel outboundorderModel, String uname);
    //导入订单
    public int importOrder(String str);
    //解析excel
    public int analysisExcel(String filePath);
    //退换货
    public int returnGoods(String jsonStr);
    //检查订单是否可以退换货
    public int checkreturn(String oid);
	//更新订单列表的订单状态
	void updateOrder(String orderStatus,String oid );
    //根据订单号查询该条退货单记录
    OrderModel selectByoId(String oId);
}
