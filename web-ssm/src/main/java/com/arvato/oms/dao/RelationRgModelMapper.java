package com.arvato.oms.dao;

import com.arvato.oms.model.test.RelationrgModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationrgModelMapper
{
    List<RelationrgModel> selectGoodsByRid(@Param("returnedId") String returnedId);
}