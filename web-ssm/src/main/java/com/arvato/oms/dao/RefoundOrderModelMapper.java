package com.arvato.oms.dao;

import com.arvato.oms.model.RefoundOrderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RefoundOrderModelMapper
{

    int insert(RefoundOrderModel refoundOrderModel);

    int insertSelective(RefoundOrderModel refoundOrderModel);

     long Count();
    long CountdDrawbackStatus(String txtvalue);
    long CountReturnedId(String txtvalue);
    long CountDrawbackId(String txtvalue);
    List<RefoundOrderModel> selectAllByReturnedId(String txtvalue ,@Param("getStartPos") int getStartPos ,@Param("getPageSize") int getPageSize );
    List<RefoundOrderModel> selectAllByDrawbackStatus(String txtvalue ,@Param("getStartPos") int getStartPos ,@Param("getPageSize") int getPageSize );

    List<RefoundOrderModel> selectAll(@Param("getStartPos") int getStartPos ,@Param("getPageSize") int getPageSize );

    List<RefoundOrderModel> selectAllByDrawbackId(String txtvalue , @Param("getStartPos") int getStartPos ,@Param("getPageSize") int getPageSize );
}