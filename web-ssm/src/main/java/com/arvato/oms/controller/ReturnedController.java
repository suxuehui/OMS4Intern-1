package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.service.ReturnedModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
@Controller
@RequestMapping("/returned")
public class ReturnedController
{
    private Logger log = Logger.getLogger(ReturnedController.class);

    @Resource
    ReturnedModelService returnedModelService;

    @RequestMapping("/cancelReturn")
    @ResponseBody
    public int cancelReturn()
    {
        /**
         * @Author: 马潇霄
         * @Description: 取消退款单
         * @Date: 15:13 2016/12/9
         * @param
         * @Return:
         */
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        int i = returnedModelService.cancelReturn(ids);
        return i;
    }

    @RequestMapping("/getAllReturned")
    @ResponseBody
    public JSONObject getAllReturned(int pageNow, int pageSize)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取全部退货单
         * @Date: 15:13 2016/12/9
         * @param
         * @Return:
         */
        JSONObject allReturnedOrders = returnedModelService.getAllReturnedOrders(pageNow, pageSize);
        return allReturnedOrders;
    }


    @RequestMapping("/getGoods")
    @ResponseBody
    public JSONObject getGoods(int pageNow, int pageSize, String returnedId)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据退货单号获得字表信息
         * @Date: 16:19 2016/12/12
         * @param pageNow 当前页数
         * @param pageSize 每页显示个数
         * @param returnedId 退货单号
         * @Return: JSONObject
         */
        JSONObject goodsListByRid = returnedModelService.getGoodsListByRid(returnedId, pageNow, pageSize);
        return  goodsListByRid;
    }

    @RequestMapping("/getReturnedBySelect")
    @ResponseBody
    public JSONObject getReturnedBySelect(int pageNow, int pageSize, String select ,String value)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页条件模糊查询
         * @Date: 16:16 2016/12/12
         * @param pageNow 当前页
         * @param pageSize 每页条数
         * @param select 条件类型1：returnedId,2:oId,3:returnedStatus,4.channelOid
         * @param value 条件内容
         * @Return: JSONObject
         */
        JSONObject getReturnedBySelect = returnedModelService.getReturnedListBySelect(select,value,pageNow,pageSize);
        return  getReturnedBySelect;
    }

}
