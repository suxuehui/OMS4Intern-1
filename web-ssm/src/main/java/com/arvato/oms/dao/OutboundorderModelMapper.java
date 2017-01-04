package com.arvato.oms.dao;

import com.arvato.oms.model.OutboundorderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OutboundorderModelMapper
{
    //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
    void updateOutboundorder(String orderStatus, String outboundState, String warehouseObid, String expressCompany, String expressId,Date time, String userName, String outboundId);

    void updateOutbound2(String orderStatus,String outboundState, Date time, String userName, String oId);

    //从出库表获取订单号
    String selectOidByOutboundId(String outboundId);

    int deleteByPrimaryKey(Integer cid);

    int insert(OutboundorderModel record);

    int insertSelective(OutboundorderModel record);

    OutboundorderModel selectByPrimaryKey(Integer cid);

    int updateByOidSelective(OutboundorderModel record);

    int updateByPrimaryKeySelective(OutboundorderModel record);

    int updateByPrimaryKey(OutboundorderModel record);

    //获取总数量
    long Count();

    long Countoid(@Param(value = "oid") String oid);

    long Countchid(@Param(value = "channeloid") String channeloid);

    long Countobid(@Param(value = "outboundid") String outboundid);


    //分页  传递pagesize pagenow参数
    List<OutboundorderModel> selectAll(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<OutboundorderModel> selectAllByOid(@Param(value = "oid") String oid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<OutboundorderModel> selectAllBychannelOid(@Param(value = "channeloid") String channeloid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<OutboundorderModel> selectAllByoutboundId(@Param(value = "outboundid") String outboundid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //精确匹配 通过oid选出所有信息
    OutboundorderModel selectByOid(String oid);


    OutboundorderModel selectOutboundorder(@Param("oid") String oid);
    //根据订单编号查询一条出库单信息（不用）

    List<OutboundorderModel> selectOutboundorders(@Param("oid") String oid);

    int updateOutboundSynchrostate(@Param("outboundorderid") String outboundorderid);


}