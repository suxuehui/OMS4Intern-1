package com.arvato.oms.dao;

import com.arvato.oms.model.OrderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderModelMapper
{
    //更新订单列表的订单状态
    void updateOrder(String orderStatus, Date time, String userName,String expressCompany,String expressId, String oid );

    void updateOrder2(String orderStatus, Date time, String userName,String oid );
    //查询全部订单
    int selectCount();

    List<OrderModel> selectAll(Integer pageNo, Integer pageSize);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderModel record);

    int insertSelective(OrderModel record);

    //根据订单状态查询全部订单
    List<OrderModel> selectAllByStatus(String status);

    //根据订单号查询订单
    OrderModel selectByOid(String oId);

    //按订单号模糊查询，分页
    int CountByCondition(@Param("data") String data,@Param("queryMode")String queryMode);

    List<OrderModel> selectByCondition(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize, @Param("data")String data,@Param("queryMode")String queryMode);

    //根据渠道订单号查询订单是否已存在
    OrderModel selectByChannelOid(String channelOid);

    OrderModel selectByPrimaryKey(Integer id);

    int updateByOidSelective(OrderModel record);

    int updateByPrimaryKeySelective(OrderModel record);

    int updateByPrimaryKey(OrderModel record);
	
	 OrderModel selectOrderByOidM(@Param("oid")String oid);
    //根据订单号查询订单

    int updateOrderStatusM(@Param("oid")String oid, @Param("orderStatus") String orderStatus);
    //更新订单状态

}