package com.arvato.oms.dao;

import com.arvato.oms.model.OutboundorderModel;
import org.springframework.stereotype.Service;

@Service
public interface OutboundorderModelMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(OutboundorderModel record);

    int insertSelective(OutboundorderModel record);

    OutboundorderModel selectByOid(String oId);

    int updateByOidSelective(OutboundorderModel record);

    int updateByPrimaryKey(OutboundorderModel record);
}