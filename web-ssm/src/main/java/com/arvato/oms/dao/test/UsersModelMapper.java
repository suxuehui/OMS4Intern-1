package com.arvato.oms.dao.test;

import com.arvato.oms.model.test.UsersModel;

public interface UsersModelMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UsersModel record);

    int insertSelective(UsersModel record);

    UsersModel selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UsersModel record);

    int updateByPrimaryKey(UsersModel record);
}