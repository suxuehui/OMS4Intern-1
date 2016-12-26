package com.arvato.oms.dao;

import com.arvato.oms.model.RelationogModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RelationogModelMapper {
    //通过returnedId选出商品与退款关系表的数据
	List<RelationogModel> selectMessageByOid(String oid);
	
    int selectCount(String oId);

    int deleteByPrimaryKey(Integer id);

    int insert(RelationogModel record);

    int insertSelective(RelationogModel record);

    Integer selectGoodsRnum(String oId);

    List<RelationogModel> selectAllByOid(String oId);

    RelationogModel selectByPrimaryKey(Integer id);

    List<RelationogModel> selectByOid(Integer pageNo,Integer pageSize,String oId);

    int updateByPrimaryKeySelective(RelationogModel record);

    int updateGoodsRnum(int id,int status);
	
	int updateByPrimaryKey(RelationogModel record);

    int countBookGoods(@Param("goodsNo")String goodsNo);
}