package com.arvato.oms.dao;

import com.arvato.oms.model.RefoundOrderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefoundOrderModelMapper
{

    int insert(RefoundOrderModel refoundOrderModel);

    int insertSelective(RefoundOrderModel refoundOrderModel);


}