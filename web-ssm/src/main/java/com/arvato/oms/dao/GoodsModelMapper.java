package com.arvato.oms.dao;

import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.GoodsPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsModelMapper {
    int deleteByPrimaryKey(String goodsno);

    int insert(GoodsModel record);

    int insertSelective(GoodsModel record);

    GoodsModel selectByPrimaryKey(String goodsno);

    List<GoodsPojo> selectByOid(Integer pageNo,Integer pageSize,String oId);

    List<GoodsPojo> selectAllByOid(String oId);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);
}