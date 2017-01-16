package com.arvato.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.deserializer.IntegerFieldDeserializer;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.service.ReturnedModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
    public JSONObject cancelReturn(Integer id, HttpServletRequest request)
    {
        /**
         * @Author: 马潇霄
         * @Description: 取消退款单
         * @Date: 15:13 2016/12/9
         * @param
         * @Return:
         */

        int i = returnedModelService.cancelReturn(id, request);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccess", i);
        return jsonObject;
    }

    @RequestMapping("/getAllReturned")
    @ResponseBody
    public JSONObject getAllReturned(int pageNow, int pageSizeR)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取全部退货单
         * @Date: 15:13 2016/12/9
         * @param
         * @Return:
         */
        return returnedModelService.getAllReturnedOrders(pageNow, pageSizeR);
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
        log.info("returnedId" + returnedId);
        return returnedModelService.getGoodsListByRid(returnedId, pageNow, pageSize);
    }

    @RequestMapping("/getReturnedBySelect")
    @ResponseBody
    public JSONObject getReturnedBySelect(int pageNow, int pageSize, String select, String value)
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

        return returnedModelService.getReturnedListBySelect(select, value, pageNow, pageSize);
    }

    @RequestMapping("/createRefoundOrder")
    @ResponseBody
    public JSONObject createRefoundOrder(Integer id)
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建退款单
         * @Date: 15:23 2016/12/14
         * @param
         * @Return:
         */

        return returnedModelService.createRefoundOrders(id);
    }

    @RequestMapping("/createOutBoundOrder")
    @ResponseBody
    public JSONObject createOutBoundOrder(Integer id, HttpServletRequest request)
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建出库单
         * @Date: 10:02 2016/12/15
         * @param
         * @Return:
         */

        return returnedModelService.createOutbound(id, request);
    }

    @RequestMapping("/createInBoundOrder")
    @ResponseBody
    public JSONObject createInBoundOrder(Integer id, HttpServletRequest request) throws UnsupportedEncodingException
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建入库单
         * @Date: 11:17 2016/12/15
         * @param
         * @Return: int 0为失败，1为成功
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return", returnedModelService.checkInBound(id, request));

        return jsonObject;
    }


    @RequestMapping("/returnedDetail")
    public ModelAndView returnedDetail(int id)
    {
        ModelAndView mov = new ModelAndView("returnedOderDetail");
        mov.addObject("a", returnedModelService.getReturnedAndGoodsByid(id));
        return mov;
    }

    @RequestMapping("/getReturnedStatus")
    @ResponseBody
    public JSONObject getReturnedStatus(String json)
    {
        JSONObject jsonObject= JSON.parseObject(json);
        String status = jsonObject.getString("status");
        ArrayList<Integer> returnIds = JSON.parseObject(jsonObject.getString("returnIds"),new TypeReference<ArrayList<Integer>>(){}) ;

        log.info("++++++++++++++++++++++++"+returnIds);
        log.info("+++++++++++++++++++++++++"+status);
        if (returnIds == null)
        {
            return null;
        }
        int success = 0;
        int exception = 0;

        for (int i = 0; i < returnIds.size(); i++)
        {
            String statusTemp = returnedModelService.getStatus(returnIds.get(i));
            if (statusTemp.equals(status))
            {
                success++;
            } else
            {
                exception++;
            }
        }
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("success", success);
        jsonObject2.put("exception", exception);
        return jsonObject2;
    }

    @RequestMapping("/getReturnedOrChange")
    @ResponseBody
    public JSONObject getReturnedOrChange(int id)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnOrChange", returnedModelService.getReturnOrChange(id));
        return jsonObject;
    }


}
