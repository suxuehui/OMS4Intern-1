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
    //分页查询全部商品信息

    GoodsModel selectOneGoodsByNo(@Param("goodsNo")String goodsNo);
    //根据商品编码精确查询商品信息

    int insertGoods(@Param("goodsNo")String goodsNo,@Param("goodsName")String goodsName,@Param("goodsTolnum")int goodsTolnum,@Param("goodsPrice")BigDecimal goodsPrice);
    //根据商品编码插入商品信息

    int deleteGoodsByNo(@Param("goodsNo")String goodsNo);
    //根据商品编码删除商品信息

    int deleteGoodsByNos(@Param("goodsNos")List<String> goodsNos);
    //根据商品编码列表删除商品信息

    List<GoodsModel> selectGoodsByNoAndPage(@Param("goodsNo")String goodsNo,@Param("startPage")int page,@Param("num") int num);
    //根据商品编码模糊分页查询商品信息

    List<GoodsModel> selectGoodsByNameAndPage(@Param("goodsName")String goodsName,@Param("startPage")int page,@Param("num") int num);
    //根据商品名称模糊分页查询商品信息

    List<GoodsModel> selectGoodsByNosAndPage(@Param("goodsNos")List<String> goodsNos,@Param("startPage")int page,@Param("num") int num);
    //根据商品编码组分页查询商品信息

    int countGoods();
    //统计商品个数
}
