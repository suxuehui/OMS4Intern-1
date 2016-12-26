package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.service.ReturnedModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @RequestMapping("/createRefoundOrder")
    @ResponseBody
    public int createRefoundOrder()
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建退款单
         * @Date: 15:23 2016/12/14
         * @param
         * @Return:
         */
       List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("221");
       return returnedModelService.createRefoundOrders(list);
    }

    @RequestMapping("/createOutBoundOrder")
    @ResponseBody
    public String createOutBoundOrder()
    {
       /**
        * @Author: 马潇霄
        * @Description: 创建出库单
        * @Date: 10:02 2016/12/15
        * @param
        * @Return:
        */
        List<String> list = new ArrayList<String>();
        list.add("RT111111111111111");
        return returnedModelService.createOutboundOrders("RT111111111111111");
    }

    @RequestMapping("/createInBoundOrder")
    @ResponseBody
    public String createInBoundOrder()
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建入库单
         * @Date: 11:17 2016/12/15
         * @param
         * @Return:  int 0为失败，1为成功
         */
        List<String> list = new ArrayList<String>();
        list.add("RT111111111111111");

        return returnedModelService.checkInBound("RT111111111111111");
    }

    public static String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
            System.out.println("utf-8 编码：" + xmlUTF8) ;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }
}
