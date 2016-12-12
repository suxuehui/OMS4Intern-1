package com.arvato.oms.dao;

import com.arvato.oms.model.OutboundorderModel;

public interface OutboundorderModelMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(OutboundorderModel record);

    int insertSelective(OutboundorderModel record);

    OutboundorderModel selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(OutboundorderModel record);

    int updateByPrimaryKey(OutboundorderModel record);
}