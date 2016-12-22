package com.arvato.oms.dao;

import com.arvato.oms.model.ExceptionModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExceptionModelMapper {

    //获取总数量
    long Count();
    long Countoid(@Param(value = "oid") String oid);
    long Countchid(@Param(value = "channeloid") String channeloid);
    long Counttype(@Param(value = "exceptionType") String exceptionType);

    //分页  传递参数
    List<ExceptionModel> selectAll(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize );
    List<ExceptionModel> selectAllByOid(@Param(value = "oid") String oid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);
    List<ExceptionModel> selectAllBychannelOid(@Param(value = "channeloid") String channeloid, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);
    List<ExceptionModel> selectAllByexceptionType(@Param(value = "exceptionType") String exceptionType, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //根据订单号删除所选异常订单
    List<ExceptionModel> deleteByOid(String  oId);

    //精确匹配 通过oid选出所有信息
    ExceptionModel  selectByExceptionOid(String oid);

    //根据订单号查询该条订单的异常类型
    String selectTypeByOid(String oId);

    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionModel record);

    int insertSelective(ExceptionModel record);

    ExceptionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionModel record);

    int updateByPrimaryKey(ExceptionModel record);
	
    int updateByOidSelective(ExceptionModel record);

 

 

    

 
}