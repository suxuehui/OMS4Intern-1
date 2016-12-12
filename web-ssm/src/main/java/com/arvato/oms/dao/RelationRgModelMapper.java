package com.arvato.oms.dao;

import com.arvato.oms.model.RelationrgModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationrgModelMapper
{
    List<RelationrgModel> selectGoodsByRid(@Param("returnedId") String returnedId, @Param("startPage")int page, @Param("num") int num);
    //根据退货单编码，分页查询商品-退货单关系表信息

    int countGoods(@Param("returnedId") String returnedId);
    //查询商品总数


}