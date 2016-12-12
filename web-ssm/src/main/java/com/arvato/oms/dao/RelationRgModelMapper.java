package com.arvato.oms.dao;

import com.arvato.oms.model.RelationRgModel;
import com.arvato.oms.model.test.RelationrgModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRgModelMapper
{


    int insert(RelationrgModel record);

    int insertSelective(RelationrgModel record);

    int updateByPrimaryKeySelective(RelationrgModel record);

    int updateByPrimaryKey(RelationrgModel record);
    //
    int deleteByPrimaryKey(Integer id);

    int insert(RelationRgModel record);

    int insertSelective(RelationRgModel record);

    RelationRgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationRgModel record);

    int updateByPrimaryKey(RelationRgModel record);


    List<RelationRgModel> selectGoodsByRid(@Param("returnedId") String returnedId);
}