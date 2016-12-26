package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
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

    @Resource
    GoodsModelMapper goodsModelMapper;

    @RequestMapping("getAllGoods")
    @ResponseBody
    public JSONObject getAllGoods(int page, int pageSize)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取所有商品信息
         * @Date: 11:20 2016/12/14
         * @param page 当前页码
         * @param pageSize 一页显示信息条数
         * @Return:  JSONObject
         */
        JSONObject jsonObject = goodsModelService.getAllGoods(page, pageSize);
        return jsonObject;
    }

    @RequestMapping("selectOneGoodsByNo")
    @ResponseBody
    public JSONObject selectOneGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据商品编码精确查询商品信息
         * @Date: 11:21 2016/12/14
         * @param goodsNo 商品编码
         * @Return:  JSONObject
         */
        JSONObject jsonObject = new JSONObject();
        if (goodsNoIsExist(goodsNo))
        {
            jsonObject = goodsModelService.selectOneGoodsByNo(goodsNo);
        }else {
            jsonObject.put("error","商品不存在");
        }
        return jsonObject;
    }


    @RequestMapping("addGoods")
    @ResponseBody
    public int addGoods(String goodsNo, String goodsName, int goodsTolnum, double goodsPriceD)
    {
        /**
         * @Author: 马潇霄
         * @Description: 添加商品
         * @Date: 11:22 2016/12/14
         * @param goodsNo
         * @param goodsName
         * @param goodsTolnum
         * @param goodsPriceD
         * @Return: int 添加商品条数，0表示失败
         */
        if (!goodsNoIsExist(goodsNo))
        {
            int i = goodsModelService.addGoods(goodsNo, goodsName, goodsTolnum, goodsPriceD);
            return i;
        }else {
            return 0;
        }
    }

    @RequestMapping("deleteGoodsByNo")
    @ResponseBody
    public int deleteGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 删除单个商品（暂不用）
         * @Date: 11:29 2016/12/14
         * @param goodsNo
         * @Return:
         */
        if (goodsNoIsExist(goodsNo))
        {
            int i = goodsModelService.deleteGoodsByNo(goodsNo);
            return i;
        }else {
            return 0;
        }
    }

    @RequestMapping("deleteGoodsByNos")
    @ResponseBody
    public int deleteGoodsByNos()
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据商品编号删除多个商品
         * @Date: 13:52 2016/12/14
         * @param
         * @Return:
         */
        List<String> list = new ArrayList<String>();
        list.add("1111113221");
        list.add("11");
        for (int i = 0; i <list.size() ; i++)
        {
            if(!goodsNoIsExist(list.get(i)))
            {
                return 0;
            }

        }
        int i = goodsModelService.deleteGoodsByNos(list);
        return i;
    }

    @RequestMapping("selectGoods")
    @ResponseBody
    public JSONObject selectGoods(String select,String value, int nowPage, int pageSize)
    {
        /**
         * @Author: 马潇霄
         * @Description: 条件查询商品
         * @Date: 13:53 2016/12/14
         * @param select 条件类型 “name”，“id”
         * @param value 条件内容
         * @param nowPage 当前页码
         * @param pageSize 每页显示个数
         * @Return:
         */
        JSONObject jsonObject = goodsModelService.selectGoodsByValueAndPage(select, value, nowPage, pageSize);
        return jsonObject;

    }

    public boolean goodsNoIsExist(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据商品编码判断商品是否存在
         * @Date: 13:54 2016/12/14
         * @param goodsNo 商品编码
         * @Return:  boolean true:商品存在 false:商品不存在
         */
        if (goodsModelMapper.countGoodsByNo(goodsNo) == 0)
        {
            return false;
        } else
        {
            return true;
        }
    }
}
