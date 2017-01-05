package com.arvato.oms.service;

import com.arvato.oms.model.RelationogModel;

import java.util.List;

/**
 * Created by GONG036 on 2016/12/9.
 */
public interface RelationogService {
    //OID 筛选
    List<RelationogModel> selectALLByOid(String oid);
/*

    //通过oid获取总数
    int selectCount(String oId);
    //分页取得商品列
    List<RelationogModel> selectByOid(Integer pageNo,Integer pageSize,String oId);
*/

    Integer selectGoodsRnum(String oId);

    Integer countGoodsNum(String goodsNo);

}


