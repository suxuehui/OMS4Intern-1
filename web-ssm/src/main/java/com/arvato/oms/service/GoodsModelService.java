package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.GoodsModel;

import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/7.
 */
public interface GoodsModelService
{
    //根据商品编号查询商品信息
    public GoodsModel selectByGoodsNo(String goodsno);

    //添加一条商品信息
    void addGoods(String goodsNo,String goodsName,String goodsVnum,String goodsPrice,String goodsTolnum,String goodsState);

    //将商品状态改为"已下架"
    void updateGoodsState(String goodsState2,String goodsTolnum2,String goodsvnum,String goodsNo2);

    public JSONObject getAllGoods(int pageNow, int num);

    public JSONObject selectOneGoodsByNo(String goodsNo);

    public int addGoods(String goodsNo,String goodsName,int goodsTolnum,double goodsPriceD);

    public int deleteGoodsByNo(String goodsNo);

    public int deleteGoodsByNos(List<String> goodNos);

    public JSONObject selectGoodsByValueAndPage(String select,String value,int nowPage,int pageSize);

    public JSONObject selectByOid(int pageNo, int pageSize, String oId);

}
