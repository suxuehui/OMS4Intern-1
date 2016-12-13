package com.arvato.oms.dao;

import com.arvato.oms.model.RefoundOrderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RefoundOrderModelMapper {
    //获取总数量
    long Count();
    long CountDrawbackId(@Param(value = "drawbackId") String drawbackId);
    long CountdDrawbackStatus(@Param(value = "drawbackStatus") String drawbackStatus);
    long CountReturnedId(@Param(value = "returnedId") String returnedId);

    //分页  传递参数
    List<RefoundOrderModel> selectAll(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize );
    List<RefoundOrderModel> selectAllByDrawbackId(@Param(value = "drawbackId") String oid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);
    List<RefoundOrderModel> selectAllByDrawbackStatus(@Param(value = "drawbackStatus") String channeloid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);
    List<RefoundOrderModel> selectAllByReturnedId(@Param(value = "returnedId") String exceptionType, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);


    int deleteByPrimaryKey(Integer id);

    int insert(RefoundOrderModel record);

    int insertSelective(RefoundOrderModel record);

    RefoundOrderModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefoundOrderModel record);

    int updateByPrimaryKey(RefoundOrderModel record);
}