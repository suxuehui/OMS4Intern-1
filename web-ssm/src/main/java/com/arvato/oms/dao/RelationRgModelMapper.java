package com.arvato.oms.dao;

import com.arvato.oms.model.RelationRgModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationRgModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationRgModel record);

    int insertSelective(RelationRgModel record);

    RelationRgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationRgModel record);

    int updateByPrimaryKey(RelationRgModel record);

    List<RelationRgModel> selectGoodsByRid(@Param("returnedId") String returnedId, @Param("startPage")int page, @Param("num") int num);
    //根据退货单编码，分页查询商品-退货单关系表信息

    int countGoods(@Param("returnedId") String returnedId);
    //查询商品总数



}