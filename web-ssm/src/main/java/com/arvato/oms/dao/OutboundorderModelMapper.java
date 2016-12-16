package com.arvato.oms.dao;

import com.arvato.oms.model.OutboundorderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OutboundorderModelMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(OutboundorderModel record);

    int insertSelective(OutboundorderModel record);

    int updateByOidSelective(OutboundorderModel record);

    int updateByPrimaryKey(OutboundorderModel record);

    //获取总数量
    long Count();
    long Countoid(@Param(value="oid") String oid);
    long Countchid(@Param(value="channeloid") String channeloid);
    long Countobid(@Param(value="outboundid") String outboundid);


    //分页  传递pagesize pagenow参数
    List<OutboundorderModel> selectAll(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize );

    List<OutboundorderModel> selectAllByOid(@Param(value="oid")String oid,@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize );

    List<OutboundorderModel> selectAllBychannelOid(@Param(value="channeloid") String channeloid,@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize );

    List<OutboundorderModel> selectAllByoutboundId(@Param(value="outboundid") String outboundid,@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize );

    //精确匹配 通过oid选出所有信息
    OutboundorderModel selectByOid(String oid);








}