package com.arvato.oms.dao;

import com.arvato.oms.model.ReturnedModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnedModelMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(ReturnedModel record);

    int insertSelective(ReturnedModel record);

    ReturnedModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReturnedModel record);

    int updateByPrimaryKey(ReturnedModel record);

    int updateStatusToDisable(@Param("ids")List<Integer> ids,@Param("returnedstatus")String returnedstatus);

    int countReturnedOrders();

    List<ReturnedModel> selectAllReturnedByPage(@Param("startPage")int page, @Param("num") int num);

}