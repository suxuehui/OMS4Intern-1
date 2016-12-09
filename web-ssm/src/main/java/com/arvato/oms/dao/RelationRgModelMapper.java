package com.arvato.oms.dao;

import com.arvato.oms.model.RelationRgModel;
import org.springframework.stereotype.Service;

@Service
public interface RelationRgModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationRgModel record);

    int insertSelective(RelationRgModel record);

    RelationRgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationRgModel record);

    int updateByPrimaryKey(RelationRgModel record);
}