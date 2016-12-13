package com.arvato.oms.dao;

import com.arvato.oms.model.ReturnedModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnedModelMapper
{
    int updateStatusToDisable(@Param("ids")List<Integer> ids,@Param("returnedstatus")String returnedstatus);

    int countReturnedOrders();

    List<ReturnedModel> selectAllReturnedByPage(@Param("startPage")int page, @Param("num") int num);

    List<ReturnedModel> selectReturnedById(@Param("startPage")int page, @Param("num") int num,@Param("returnedId") String returnedId);
    //根据退货单编码分页模糊查询退货单信息

    int countReturnedbyId(@Param("returnedId") String returnedId);
    //按退货单号模糊查询退货单记录数

    List<ReturnedModel> selectReturnedByOId(@Param("startPage")int page, @Param("num") int num,@Param("oId") String oId);
    //根据订单号分页模糊查询退货单信息

    int countReturnedbyOId(@Param("oId") String oId);
    //按订单号模糊查询退货单记录数

    List<ReturnedModel> selectReturnedByStatus(@Param("startPage")int page, @Param("num") int num,@Param("returnedStatus") String returnedStatus);
    //根据退货单状态分页模糊查询退货单信息

    int countReturnedbyStatus(@Param("returnedStatus") String returnedStatus);
    //按退货单状态模糊查询退货单记录数

    List<ReturnedModel> selectReturnedByChannelOid(@Param("startPage")int page, @Param("num") int num,@Param("channelOid") String channelOid);
    //根据渠道订单号分页模糊查询退货单信息

    int countReturnedbyChannelOid(@Param("channelOid") String channelOid);
    //按渠道订单号模糊查询退货单记录数

    ReturnedModel selectOneReturnedById(@Param("returnedId") String returnedId);

    int updateReturnedStatus(@Param("returnedId") String returnedId,@Param("returnedstatus")String returnedstatus);
    //更新一条退货单状态
}