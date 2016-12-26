package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.service.GoodsModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/8.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController
{
    private Logger log = Logger.getLogger(GoodsController.class);

    @Resource
    GoodsModelService goodsModelService;

    @RequestMapping("getAllGoods")
    @ResponseBody
    public JSONObject getAllGoods(int page, int pageSize)
    {
        JSONObject jsonObject = goodsModelService.getAllGoods(page, pageSize);
        return jsonObject;
    }

    @RequestMapping("selectOneGoodsByNo")
    @ResponseBody
    public JSONObject selectOneGoodsByNo(String goodsNo)
    {
        JSONObject jsonObject = goodsModelService.selectOneGoodsByNo(goodsNo);
        return jsonObject;
    }

    @RequestMapping("addGoods")
    @ResponseBody
    public int addGoods(String goodsNo, String goodsName, int goodsTolnum, double goodsPriceD)
    {
        int i = goodsModelService.addGoods(goodsNo, goodsName, goodsTolnum, goodsPriceD);
        return i;
    }

    @RequestMapping("deleteGoodsByNo")
    @ResponseBody
    public int deleteGoodsByNo(String goodsNo)
    {
        int i = goodsModelService.deleteGoodsByNo(goodsNo);
        return i;
    }

    @RequestMapping("deleteGoodsByNos")
    @ResponseBody
    public int deleteGoodsByNos()
    {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        int i = goodsModelService.deleteGoodsByNos(list);
        return i;
    }

    @RequestMapping("selectGoods")
    @ResponseBody
    public JSONObject selectGoods(String select,String value, int nowPage, int pageSize)
    {
        JSONObject jsonObject = goodsModelService.selectGoodsByValueAndPage(select, value, nowPage, pageSize);
        return jsonObject;

    }
}
