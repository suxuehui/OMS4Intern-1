package com.arvato.oms.dao;

import com.arvato.oms.model.OrderModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderModelMapper {

        int selectCount();

        int deleteByPrimaryKey(Integer id);

        int insert(OrderModel record);

        int insertSelective(OrderModel record);

        OrderModel selectByOid(String oId);
        //按订单号模糊查询，分页
        int CountByOid(String oId);

        List<OrderModel> selectByOids(Integer pageNo,Integer pageSize,String oId);
        //按渠道订单号模糊查询，分页
        int CountByChanneloid(String channelOid);

        List<OrderModel> selectByChanneloids(Integer pageNo,Integer pageSize,String channelOid);
        //按订单状态模糊查询，分页
        int CountByOrderStatus(String orderStatus);

        List<OrderModel> selectByOrderStatuss(Integer pageNo,Integer pageSize,String orderStatus);
        //按支付方式模糊查询，分页
        int CountByPayStyle(String payStyle);

        List<OrderModel> selectByPayStyles(Integer pageNo,Integer pageSize,String payStyle);
        //按物流公司模糊查询，分页
        int CountByLogisticsCompany(String logisticsCompany);

        List<OrderModel> selectByLogisticsCompanys(Integer pageNo,Integer pageSize,String logisticsCompany);
        //按省模糊查询，分页
        int CountByReceiverProvince(String receiverProvince);

        List<OrderModel> selectByReceiverProvinces(Integer pageNo,Integer pageSize,String receiverProvince);
        //按市模糊查询，分页
        int CountByReceiverCity(String receiverCity);

        List<OrderModel> selectByReceiverCitys(Integer pageNo,Integer pageSize,String receiverCity);
        //按区模糊查询，分页
        int CountByReceiverArea(String receiverArea);

        List<OrderModel> selectByReceiverAreas(Integer pageNo,Integer pageSize,String receiverArea);
        //按收货人手机号模糊查询，分页
        int CountByReceiverMobel(String receiverMobel);

        List<OrderModel> selectByReceiverMobels(Integer pageNo,Integer pageSize,String receiverMobel);

        //根据渠道订单号查询订单是否已存在
        OrderModel selectByChannelOid(String channelOid);

        List<OrderModel> selectAll(Integer pageNo,Integer pageSize);

        int updateByOidSelective(OrderModel record);

        int updateByPrimaryKey(OrderModel record);
}