package com.arvato.oms.dao;

import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.GoodsPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface GoodsModelMapper {
    //根据商品编号查询商品信息
    public GoodsModel selectByGoodsNo(String goodsno);

    //添加一条商品信息
    void addGoods(String goodsNo,String goodsName,String goodsVnum,String goodsPrice,String goodsTolnum,String goodState);

    //将商品状态改为"已下架"
    void updateGoodsState(String goodsState2,String goodsTolnum2,Integer goodsvnum,String goodsNo2);

    int deleteByPrimaryKey(String goodsno);

    int insert(GoodsModel record);

    int insertSelective(GoodsModel record);
	
	GoodsModel selectByPrimaryKey(String goodsno);

    List<GoodsPojo> selectByOid(Integer pageNo,Integer pageSize,String oId);

    List<GoodsPojo> selectAllByOid(String oId);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);


    /**
     * Created by 马潇霄 on 2016/12/7.
     */
    List<GoodsModel> selectAllGoodsByPage(@Param("startPage")int page, @Param("num") int num);
    //分页查询全部商品信息

    GoodsModel selectOneGoodsByNo(@Param("goodsNo")String goodsNo);
    //根据商品编码精确查询商品信息

    int insertGoods(@Param("goodsNo")String goodsNo,@Param("goodsName")String goodsName,@Param("goodsTolnum")int goodsTolnum,@Param("goodsPrice")BigDecimal goodsPrice);
    //根据商品编码插入商品信息

    int deleteGoodsByNo(@Param("goodsNo")String goodsNo);
    //根据商品编码删除商品信息

    int deleteGoodsByNos(@Param("goodsNos")List<String> goodsNos);
    //根据商品编码列表删除商品信息

    List<GoodsModel> selectGoodsByNoAndPage(@Param("goodsNo")String goodsNo, @Param("startPage")int page, @Param("num") int num);
    //根据商品编码模糊分页查询商品信息

    int countGoodsByNoAndPage(@Param("goodsNo")String goodsNo);
    //根据商品编码模糊分页查询商品信息 记录数

    List<GoodsModel> selectGoodsByNameAndPage(@Param("goodsName")String goodsName,@Param("startPage")int page,@Param("num") int num);
    //根据商品名称模糊分页查询商品信息

    int countGoodsByNameAndPage(@Param("goodsName")String goodsName);
    //根据商品名称模糊分页查询商品信息记录数

    List<GoodsModel> selectGoodsByNosAndPage(@Param("goodsNos")List<String> goodsNos,@Param("startPage")int page,@Param("num") int num);
    //根据商品编码组分页查询商品信息

    int countGoods();
    //统计商品个数

    int countGoodsByNo(@Param("goodsNo")String goodsNo);
    //根据商品编码查询商品记录数



}