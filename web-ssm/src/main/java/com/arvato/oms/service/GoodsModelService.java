package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.GoodsModel;

import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/7.
 */
public interface GoodsModelService
{
    public JSONObject getAllGoods(int pageNow, int num);

    public JSONObject selectOneGoodsByNo(String goodsNo);

    public int addGoods(String goodsNo,String goodsName,int goodsTolnum,double goodsPriceD);

    public int deleteGoodsByNo(String goodsNo);

    public int deleteGoodsByNos(List<String> goodNos);

    public JSONObject selectGoodsByValueAndPage(String select,String value,int nowPage,int pageSize);

    public JSONObject selectByOid(int pageNo, int pageSize, String oId);

    public GoodsModel selectByGoodsNo(String goodsno);

}
