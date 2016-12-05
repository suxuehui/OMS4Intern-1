package com.arvato.oms.dao;

import com.arvato.oms.model.UsersModel;

public interface UsersModelMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UsersModel record);

    int insertSelective(UsersModel record);

    UsersModel selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UsersModel record);

    int updateByPrimaryKey(UsersModel record);
}