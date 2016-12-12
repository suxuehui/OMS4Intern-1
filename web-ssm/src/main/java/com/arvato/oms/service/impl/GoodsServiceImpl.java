package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Service
public class GoodsServiceImpl implements GoodsService
{
    @Resource
    RelationogModelMapper relationogModelMapper;
    @Resource
    GoodsModelMapper goodsModelMapper;
    public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    /*public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    {
        int num=relationogModelMapper.selectCount(oId);
        int pageTotal=num/pageSize+1;
        int count=(pageNo-1)*pageSize;
        List<GoodsPojo> goodsPojos=goodsModelMapper.selectByOid(count,pageSize,oId);
        JSONObject jObj=new JSONObject();
        jObj.put("pageTotal",pageTotal);
        jObj.put("goodsPojos",goodsPojos);
        jObj.put("pageNo",pageNo);
        return jObj;
    }*/

    public JSONObject selectByOid(int pageNo, int pageSize, String oId) {
        return null;
    }

    public GoodsModel selectByGoodsNo(String goodsno) {
        return null;
    }
}
