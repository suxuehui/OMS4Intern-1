package com.arvato.oms.dao;

import com.arvato.oms.model.ReturnedModel;
import org.springframework.stereotype.Service;

@Service
public interface ReturnedModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReturnedModel record);

    int insertSelective(ReturnedModel record);

    ReturnedModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReturnedModel record);

    int updateByPrimaryKey(ReturnedModel record);
}