package com.arvato.oms.dao;

import com.arvato.oms.model.InboundorderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface InboundorderModelMapper
{

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
    InboundorderModel selectByOid(String oid);

    //更新入库单列表
    int updateByInboundId(@Param("inboundid") String inboundid, @Param("inboundstate") String inboundstate,@Param("modifytime") Date modifytime);


    int insertSelective(InboundorderModel record);
    //生成新入库单

    int countInboundorder(@Param("inboundorderid") String inboundorderid);
    //统计参数入库单号在数据库中个数

    int updateInboundSynchrostate(@Param("inboundorderid") String inboundorderid);
    //


}