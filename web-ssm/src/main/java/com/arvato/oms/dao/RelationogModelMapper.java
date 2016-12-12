package com.arvato.oms.dao;

import com.arvato.oms.model.RelationogModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RelationogModelMapper {
    int selectCount(String oId);

    int deleteByPrimaryKey(Integer id);

    int insert(RelationogModel record);

    int insertSelective(RelationogModel record);

    List<RelationogModel> selectAllByOid(String oId);

    RelationogModel selectByPrimaryKey(Integer id);

    List<RelationogModel> selectByOid(Integer pageNo,Integer pageSize,String oId);

    int updateByPrimaryKeySelective(RelationogModel record);

    int updateByPrimaryKey(RelationogModel record);
}