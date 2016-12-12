package com.arvato.oms.dao;

import com.arvato.oms.model.InboundorderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InboundorderDao {

    //获取总数量
    long Count();
    long Countoid(@Param(value = "oid") String oid);
    long Countchid(@Param(value = "channeloid") String channeloid);
    long Countobid(@Param(value = "inboundid") String inboundid);
    long Countrid(@Param(value = "returnedid") String returnedid);


    //分页  传递参数
    List<InboundorderModel> selectAll(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<InboundorderModel> selectAllByOid(@Param(value = "oid") String oid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<InboundorderModel> selectAllBychannelOid(@Param(value = "channeloid") String channeloid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<InboundorderModel> selectAllByoutboundId(@Param(value = "inboundid") String inboundid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<InboundorderModel> selectAllByreturnedId(@Param(value = "returnedid") String returnedid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //精确匹配 通过oid选出所有信息
    InboundorderModel  selectByOid(String oid);






}