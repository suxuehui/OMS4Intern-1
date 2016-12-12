package com.arvato.oms.dao;

import com.arvato.oms.model.RelationrgModel;
import org.springframework.stereotype.Service;

@Service
public interface RelationrgModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationrgModel record);

    int insertSelective(RelationrgModel record);

    RelationrgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationrgModel record);

    int updateByPrimaryKey(RelationrgModel record);
}