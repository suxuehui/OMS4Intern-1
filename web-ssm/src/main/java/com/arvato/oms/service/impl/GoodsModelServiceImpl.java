package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.pojo.GoodsAndStatus;
import com.arvato.oms.service.GoodsModelService;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by 马潇霄 on 2016/12/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsModelServiceImpl implements GoodsModelService
{
    @Resource
    RelationogModelMapper relationogModelMapper;
	
    @Resource
    private GoodsModelMapper goodsModelMapper;

    private Logger log = Logger.getLogger(GoodsModelServiceImpl.class);

    //订单页面的
    public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    {
        int num= relationogModelMapper.selectCount(oId);
        int pageTotal=num/pageSize+1;
        int count=(pageNo-1)*pageSize;
        List<GoodsPojo> goodsPojos=goodsModelMapper.selectByOid(count,pageSize,oId);
        JSONObject jObj=new JSONObject();
        jObj.put("pageTotal",pageTotal);
        jObj.put("goodsPojos",goodsPojos);
        jObj.put("pageNo",pageNo);
        return jObj;
    }
    public JSONObject getAllGoods(int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取商品信息
         * @Date: 17:27 2016/12/7
         * @param pageNow 当前页数
         * @param num 每页需要显示的商品信息条数
         * @Return: List<GoodsModel>
         */
        Integer countUser = goodsModelMapper.countGoods();
        Page page = new Page(countUser,pageNow,num);
        List<GoodsModel> goodsModels = goodsModelMapper.selectAllGoodsByPage(page.getStartPos(), num);
        List<GoodsAndStatus> goodsAndStatus = new ArrayList<GoodsAndStatus>();
        for (int i = 0; i < goodsModels.size(); i++)
        {
            GoodsModel goodsModel = goodsModels.get(i);
            GoodsAndStatus goods = new GoodsAndStatus();
            goods.setBooknum(relationogModelMapper.countBookGoods(goodsModel.getGoodsno()));
            goods.setGoodsname(goodsModel.getGoodsname());
            goods.setGoodsno(goodsModel.getGoodsno());
            goods.setGoodsprice(goodsModel.getGoodsprice());
            goods.setGoodstolnum(goodsModel.getGoodstolnum());
            goods.setGoodsvnum(goodsModel.getGoodsvnum());
            goodsAndStatus.add(goods);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsAndStatus1", goodsAndStatus);
        jsonObject.put("pageNow", pageNow);
        jsonObject.put("pageTotal", page.getTotalPageCount());
        log.info("StartPos:" + page.getStartPos());

        return jsonObject;
    }

    public JSONObject selectOneGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 按商品编号精确查询商品信息
         * @Date: 14:18 2016/12/8
         * @param goodsNo
         * @Return: JSONObject
         */
        JSONObject json = new JSONObject();
        GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(goodsNo);
        GoodsAndStatus goods = new GoodsAndStatus();
        goods.setBooknum(relationogModelMapper.countBookGoods(goodsModel.getGoodsno()));
        goods.setGoodsname(goodsModel.getGoodsname());
        goods.setGoodsno(goodsModel.getGoodsno());
        goods.setGoodsprice(goodsModel.getGoodsprice());
        goods.setGoodstolnum(goodsModel.getGoodstolnum());
        goods.setGoodsvnum(goodsModel.getGoodsvnum());
        json.put("goods", goods);
        return json;
    }

    public int addGoods(String goodsNo, String goodsName, int goodsTolnum, double goodsPriceD)
    {
        /**
         * @Author: 马潇霄
         * @Description: 添加商品信息
         * @Date: 15:31 2016/12/8
         * @param goodsNo 商品编码
         * @param goodsName 商品名称
         * @param goodsTolnum 商品总库存
         * @param goodsPriceD 商品价格
         * @Return:  int 1为插入成功，0为插入失败
         */
        BigDecimal goodsPrice = BigDecimal.valueOf(goodsPriceD);
        return goodsModelMapper.insertGoods(goodsNo, goodsName, goodsTolnum, goodsPrice);

    }

    public int deleteGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据商品编号删除商品信息
         * @Date: 16:43 2016/12/8
         * @param goodsNo
         * @Return:  int 1为插入成功，0为插入失败
         */
        return goodsModelMapper.deleteGoodsByNo(goodsNo);

    }

    public int deleteGoodsByNos(List<String> goodNos)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据多个商品编码删除商品信息
         * @Date: 17:08 2016/12/8
         * @param goodNos
         * @Return: int 1为插入成功，0为插入失败
         */
        return goodsModelMapper.deleteGoodsByNos(goodNos);

    }

    public JSONObject selectGoodsByValueAndPage(String select, String value, int nowPage, int pageSize)
    {

        /**
         * @Author: 马潇霄
         * @Description: 根据商品名称，或商品编码模糊查询商品
         * @Date: 9:43 2016/12/9
         * @param select 查询条件
         * @param value 查询内容
         * @param nowPage 当前页
         * @param pageSize 每页显示的数量
         * @Return: JSONObject
         */
        JSONObject json = new JSONObject();
        if ("name".equals(select))
        {
            int goodsCount = goodsModelMapper.countGoodsByNameAndPage(value);
            Page page = new Page(goodsCount,nowPage, pageSize);
            int startPage = page.getStartPos();
            List<GoodsModel> goodsModels = goodsModelMapper.selectGoodsByNameAndPage(value, startPage, pageSize);
            List<GoodsAndStatus> goodsAndStatus = new ArrayList<GoodsAndStatus>();
            for (int i = 0; i < goodsModels.size(); i++)
            {
                GoodsModel goodsModel = goodsModels.get(i);
                GoodsAndStatus goods = new GoodsAndStatus();
                goods.setBooknum(relationogModelMapper.countBookGoods(goodsModel.getGoodsno()));
                goods.setGoodsname(goodsModel.getGoodsname());
                goods.setGoodsno(goodsModel.getGoodsno());
                goods.setGoodsprice(goodsModel.getGoodsprice());
                goods.setGoodstolnum(goodsModel.getGoodstolnum());
                goods.setGoodsvnum(goodsModel.getGoodsvnum());
                goodsAndStatus.add(goods);
            }
            json.put("goodsAndStatus", goodsAndStatus);
            json.put("size", goodsAndStatus.size());
            json.put("totalPage", page.getTotalPageCount());

        } else if ("id".equals(select))
        {
            int goodsCount = goodsModelMapper.countGoodsByNoAndPage(value);
            Page page = new Page(goodsCount,nowPage, pageSize);
            int startPage = page.getStartPos();
            List<GoodsModel> goodsModels = goodsModelMapper.selectGoodsByNoAndPage(value, startPage, pageSize);
            List<GoodsAndStatus> goodsAndStatus = new ArrayList<GoodsAndStatus>();
            for (int i = 0; i < goodsModels.size(); i++)
            {
                GoodsModel goodsModel = goodsModels.get(i);
                GoodsAndStatus goods = new GoodsAndStatus();
                goods.setBooknum(relationogModelMapper.countBookGoods(goodsModel.getGoodsno()));
                goods.setGoodsname(goodsModel.getGoodsname());
                goods.setGoodsno(goodsModel.getGoodsno());
                goods.setGoodsprice(goodsModel.getGoodsprice());
                goods.setGoodstolnum(goodsModel.getGoodstolnum());
                goods.setGoodsvnum(goodsModel.getGoodsvnum());
                goodsAndStatus.add(goods);

            }
            json.put("goodsAndStatus", goodsAndStatus);
            json.put("size", goodsAndStatus.size());
            json.put("totalPage", page.getTotalPageCount());
        }


        return json;
    }

    //通过goodsno获取一件商品的全部信息
    public GoodsModel selectByGoodsNo(String goodsno) {
        return goodsModelMapper.selectByGoodsNo(goodsno);

    }

    //添加一条商品信息
    public void addGoods(String goodsNo, String goodsName, String goodsVnum, String goodsPrice, String goodsTolnum, String goodsState) {
        goodsModelMapper.addGoods(goodsNo,goodsName,goodsVnum,goodsPrice,goodsTolnum,goodsState);
    }

    //将商品状态改为"已下架"
    public void updateGoodsState(String goodsState2,String goodsTolnum2,String goodsvnum,String goodsNo2) {
        goodsModelMapper.updateGoodsState(goodsState2,goodsTolnum2,goodsvnum,goodsNo2);
    }


}

