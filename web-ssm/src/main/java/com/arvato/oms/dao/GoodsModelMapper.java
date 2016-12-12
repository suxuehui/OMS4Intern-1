package com.arvato.oms.dao;

import com.arvato.oms.model.GoodsModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/7.
 */
@Repository
public interface GoodsModelMapper
{
    List<GoodsModel> selectAllGoodsByPage(@Param("startPage")int page,@Param("num") int num);

    GoodsModel selectOneGoodsByNo(@Param("goodsNo")String goodsNo);

    int insertGoods(@Param("goodsNo")String goodsNo,@Param("goodsName")String goodsName,@Param("goodsTolnum")int goodsTolnum,@Param("goodsPrice")BigDecimal goodsPrice);

    int deleteGoodsByNo(@Param("goodsNo")String goodsNo);

    int deleteGoodsByNos(@Param("goodsNos")List<String> goodsNos);

    List<GoodsModel> selectGoodsByNoAndPage(@Param("goodsNo")String goodsNo,@Param("startPage")int page,@Param("num") int num);

    List<GoodsModel> selectGoodsByNameAndPage(@Param("goodsName")String goodsName,@Param("startPage")int page,@Param("num") int num);

    List<GoodsModel> selectGoodsByNosAndPage(@Param("goodsNos")List<String> goodsNos,@Param("startPage")int page,@Param("num") int num);

    int countGoods();


    int deleteByPrimaryKey(String goodsno);

    int insert(GoodsModel  record);

    int insertSelective(GoodsModel record);

    GoodsModel selectByGoodsNo(String goodsno);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);


}
