package com.arvato.oms.dao;

import com.arvato.oms.model.GoodsModel;
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

}