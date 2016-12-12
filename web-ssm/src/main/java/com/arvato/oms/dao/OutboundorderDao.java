package com.arvato.oms.dao;

import com.arvato.oms.model.OutboundorderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutboundorderDao {



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
    List<OutboundorderModel> selectByOid(String oid);

    OutboundorderModel  selectByOid(String oid);
}