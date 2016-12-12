package com.arvato.oms.dao;

import com.arvato.oms.model.ExceptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExceptionModelMapper {

    //获取总数量
    long Count();

    //分页  传递参数
    List<ExceptionModel>  selectAll(@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize );

    //通过订单号查询
    List<ExceptionModel> selectByOid(String oId);

    //通过渠道订单号查询
    List<ExceptionModel> selectByChannelOid(String channelOid);

    //通过异常状态查询
    List<ExceptionModel> selectByExceptionStatus(String exceptionStatus);
    //ZHANG
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionModel record);

    int insertSelective(ExceptionModel record);

    ExceptionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionModel record);

    int updateByPrimaryKey(ExceptionModel record);



}