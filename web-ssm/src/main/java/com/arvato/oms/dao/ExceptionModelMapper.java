package com.arvato.oms.dao;

import com.arvato.oms.model.ExceptionModel;
import org.springframework.stereotype.Service;

@Service
public interface ExceptionModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionModel record);

    int insertSelective(ExceptionModel record);

    ExceptionModel selectByOid(String oId);

    int updateByOidSelective(ExceptionModel record);

    int updateByPrimaryKey(ExceptionModel record);
}